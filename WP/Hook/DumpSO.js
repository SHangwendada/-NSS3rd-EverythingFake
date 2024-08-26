function hook_memcmp_addr() {
    //hook反调试
    var memcmp_addr = Module.findExportByName("libc.so", "fread");
    if (memcmp_addr !== null) {
       // console.log("fread address: ", memcmp_addr);
        Interceptor.attach(memcmp_addr, {
            onEnter: function (args) {
                this.buffer = args[0];   // 保存 buffer 参数
                this.size = args[1];     // 保存 size 参数
                this.count = args[2];    // 保存 count 参数
                this.stream = args[3];   // 保存 FILE* 参数
            },
            onLeave: function (retval) {
            //    console.log(this.count.toInt32());
                if (this.count.toInt32() == 8) {
                    Memory.writeByteArray(this.buffer, [0x50, 0x00, 0x00, 0x58, 0x00, 0x02, 0x1f, 0xd6]);
                    retval.replace(8); // 填充前8字节
                    //console.log(hexdump(this.buffer));
                }
            }
        });
    } else {
      //  console.log("Error: memcmp function not found in libc.so");
    }
}
function hook_RegisterNatives() {
    var symbols = Module.enumerateSymbolsSync("libart.so");
    var addrRegisterNatives = null;
    for (var i = 0; i < symbols.length; i++) {
        var symbol = symbols[i];

        //_ZN3art3JNI15RegisterNativesEP7_JNIEnvP7_jclassPK15JNINativeMethodi
        if (symbol.name.indexOf("art") >= 0 &&
            symbol.name.indexOf("JNI") >= 0 &&
            symbol.name.indexOf("RegisterNatives") >= 0 &&
            symbol.name.indexOf("CheckJNI") < 0) {
            addrRegisterNatives = symbol.address;
            console.log("RegisterNatives is at ", symbol.address, symbol.name);
        }
    }

    if (addrRegisterNatives != null) {
        Interceptor.attach(addrRegisterNatives, {
            onEnter: function (args) {
                console.log("[RegisterNatives] method_count:", args[3]);
                var env = args[0];
                var java_class = args[1];
                var class_name = Java.vm.tryGetEnv().getClassName(java_class);
                //console.log(class_name);

                var methods_ptr = ptr(args[2]);

                var method_count = parseInt(args[3]);
                for (var i = 0; i < method_count; i++) {
                    var name_ptr = Memory.readPointer(methods_ptr.add(i * Process.pointerSize * 3));
                    var sig_ptr = Memory.readPointer(methods_ptr.add(i * Process.pointerSize * 3 + Process.pointerSize));
                    var fnPtr_ptr = Memory.readPointer(methods_ptr.add(i * Process.pointerSize * 3 + Process.pointerSize * 2));

                    var name = Memory.readCString(name_ptr);
                    var sig = Memory.readCString(sig_ptr);
                    var find_module = Process.findModuleByAddress(fnPtr_ptr);
                    console.log("[RegisterNatives] java_class:", class_name, "name:", name, "sig:", sig, "fnPtr:", fnPtr_ptr, "module_name:", find_module.name, "module_base:", find_module.base, "offset:", ptr(fnPtr_ptr).sub(find_module.base));
                    if (class_name.indexOf("MainActivity") != -1) {
                        dump_so("libezezez.so");
                    }
                }
            }
        });
    }
}


function hookOpen() {
    var FakeMaps = "/data/data/com.swdd.ezezez/maps";
    var openPtr = Module.getExportByName(null, 'open');
    const open = new NativeFunction(openPtr, 'int', ['pointer', 'int']);
    var readPtr = Module.findExportByName("libc.so", "read");

    Interceptor.replace(openPtr, new NativeCallback(function (fileNamePtr, flag) {
        var FD = open(fileNamePtr, flag);
        var fileName = fileNamePtr.readCString();

        if (fileName.indexOf("maps") >= 0) {
            console.warn("[Warning]->mapsRedirect Success");
            var filename = Memory.allocUtf8String(FakeMaps);
            return open(filename, flag);
        }
        return FD;
    }, 'int', ['pointer', 'int']))
}

function dump_so(so_name) {
    var libso = Process.getModuleByName(so_name);
    console.log("[name]:", libso.name);
    console.log("[base]:", libso.base);
    console.log("[size]:", ptr(libso.size));
    console.log("[path]:", libso.path);
    var file_path = "/data/data/com.swdd.ezezez/" + libso.name + "_" + libso.base + "_" + ptr(libso.size) + ".so";
    var file_handle = new File(file_path, "wb");
    if (file_handle && file_handle != null) {
        Memory.protect(ptr(libso.base), libso.size, 'rwx');
        var libso_buffer = ptr(libso.base).readByteArray(libso.size);
        file_handle.write(libso_buffer);
        file_handle.flush();
        file_handle.close();
        console.log("[dump]:", file_path);
    }

}
function hook_android_dlopen_ext() {
    Interceptor.attach(Module.findExportByName(null, "dlopen"), {
        onEnter: function (args) {
            this.name = args[0].readCString();
            console.log(this.name);
            if (this.name.indexOf("libotherso.so") >= 0) {

                console.log(this.name);
                var symbols = Process.getModuleByName("linker64").enumerateSymbols();
                var callConstructorAdd = null;
                for (var index = 0; index < symbols.length; index++) {
                    const symbol = symbols[index];
                    if (symbol.name.indexOf("__dl__ZN6soinfo17call_constructorsEv") != -1) {
                        callConstructorAdd = symbol.address;
                    }
                }
                console.log("callConstructorAdd -> " + callConstructorAdd);

                var isHook = false;
                Interceptor.attach(callConstructorAdd, {
                    onEnter: function (args) {
                        if (!isHook) {


                            dump_so("libotherso.so");

                            isHook = true;
                        }
                    },
                    onLeave: function () { }
                });

            }
        }, onLeave: function () { }
    });
}


function hook_dlopen() {
    Interceptor.attach(Module.findExportByName(null, "dlopen"),
        {
            onEnter: function (args) {
                var pathptr = args[0];
                if (pathptr !== undefined && pathptr != null) {
                    var path = ptr(pathptr).readCString();
                    console.log(path);
                    if (path.indexOf("libotherso") >= 0) {

                        this.is_can_hook = true;
                    }
                }
            }, onLeave: function () {  }
        }
    );
}


function hookConstructor() {
    var symbols = Process.getModuleByName("linker64").enumerateSymbols();
    var callArray = null;
    for (var index = 0; index < symbols.length; index++) {
        const symbol = symbols[index];

        //console.log(symbol.name);
        if (symbol.name.indexOf("__dl__ZL10call_arrayIPFviPPcS1_EEvPKcPT_mbS5_") != -1) {
            callArray = symbol.address;

            break;
        }
    }

    Interceptor.attach(callArray, {
        onEnter: function (args) {
            var pathName = args[3].readCString();
            console.log("[pathName]->",pathName)
        }, onLeave: function (reval) { }
    })
}


setImmediate(hook_memcmp_addr);
//setImmediate(hookOpen);
//setImmediate(hookConstructor);
setImmediate(hook_RegisterNatives);
//setImmediate(my_hook_dlopen, "libotherso.so");