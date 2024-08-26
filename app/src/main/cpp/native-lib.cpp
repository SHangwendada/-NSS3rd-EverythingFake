#include <jni.h>
#include <string>
#include <jni.h>
#include <string>
#include <link.h>
#include <dlfcn.h>
#include "elf.h"
#include "sys/stat.h"
#include "fcntl.h"
#include "sys/mman.h"
#include "android/log.h"
#include "errno.h"
#include "MyLoader.h"
#include "ida.h"
#include "obfusheader.h"
#include <jni.h>
#include <string>
#include <cstring>
#include <android/log.h>
#include <pthread.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <dlfcn.h>
#include "dlfcn/local_dlfcn.h"
#define FLAG_LINKED     0x00000001
#define FLAG_EXE        0x00000004 // The main executable
#define FLAG_LINKER     0x00000010 // The linker itself
#define FLAG_GNU_HASH   0x00000040 // uses gnu hash
#define FLAG_NEW_SOINFO 0x40000000 // new soinfo format
#define SUPPORTED_DT_FLAGS_1 (DF_1_NOW | DF_1_GLOBAL | DF_1_NODELETE)
#define SOINFO_VERSION 2
#if defined(__aarch64__) || defined(__x86_64__)
#define USE_RELA 1
#endif
#define MAYBE_MAP_FLAG(x, from, to)  (((x) & (from)) ? (to) : 0)
#define PFLAGS_TO_PROT(x)            (MAYBE_MAP_FLAG((x), PF_X, PROT_EXEC) | \
                                   MAYBE_MAP_FLAG((x), PF_R, PROT_READ) | \
                              MAYBE_MAP_FLAG((x), PF_W, PROT_WRITE))
#define PAGE_START(x) ((x) & PAGE_MASK)
#define PAGE_START(x)  ((x) & PAGE_MASK)
#define PAGE_OFFSET(x) ((x) & ~PAGE_MASK)
#define PAGE_END(x)    PAGE_START((x) + (PAGE_SIZE-1))
#define  TAG    "SWDD"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)
static void* check(void* arg) {
    while ((1)){
#ifdef __LP64__
        const char *lib_path = "/system/lib64/libc.so";
#else
        const char *lib_path = "/system/lib/libc.so";
#endif
#define CMP_COUNT 8
        const char *sym_name = "signal";

        struct local_dlfcn_handle *handle = static_cast<local_dlfcn_handle *>(local_dlopen(lib_path));

        off_t offset = local_dlsym(handle,sym_name);

        FILE *fp = fopen(lib_path,"rb");
        char file_bytes[CMP_COUNT] = {0};
        if(fp != NULL){
                fseek(fp,offset,SEEK_SET);
                fread(file_bytes,1,CMP_COUNT,fp);
                fclose(fp);
            }

        void *dl_handle = dlopen(lib_path,RTLD_NOW);
        void *sym = dlsym(dl_handle,sym_name);

        int is_hook = memcmp(file_bytes,sym,CMP_COUNT) != 0;

        local_dlclose(handle);
        dlclose(dl_handle);
        if (is_hook){
                //  LOGI("FIND!Hook!");
                exit(0);
            }
        sleep(1);
    }

}

static void* check_ports(void* arg) {
/*    <uses-permission android:name="android.permission.INTERNET" />*/
    while (1) {
        struct sockaddr_in sa{};
        sa.sin_family = AF_INET;
        auto port = 23946;
        sa.sin_port = htons(port = 23946);
        inet_aton("127.0.0.1", &sa.sin_addr);
        int sock = socket(AF_INET, SOCK_STREAM, 0);
        if (connect(sock, (struct sockaddr *) &sa, sizeof(sa)) == 0) {
                LOGI("Find! IDA!");
                close(sock);
                exit(0);
            }
        sleep(3);
    }
}

static void __attribute__((constructor())) anti() {
    pthread_t tid;
    LOGI("GO!");
    if (pthread_create(&tid, NULL, check_ports, NULL) != 0) {
            perror("Failed to create thread");
            exit(EXIT_FAILURE);
    }
    if (pthread_create(&tid, NULL, check, NULL) != 0) {
            perror("Failed to create thread");
            exit(EXIT_FAILURE);
    }
}




JNIEXPORT void JNICALL
static start(JNIEnv *env, jobject thiz) {
    WATERMARK();
    JavaVM* javaVM;
    env->GetJavaVM(&javaVM);
   // MyLoader myLoader;
   // myLoader.run("/data/local/tmp/libezezez1.so");
    jclass mainActivityClass = env->FindClass("com/swdd/ezezez/MainActivity");

    // 获取 getAppDirPath 方法的ID
    jmethodID getAppDirPathMethod = env->GetStaticMethodID(mainActivityClass, "getAppDirPath", "(Landroid/content/Context;)Ljava/lang/String;");

    // 调用 getAppDirPath 方法获取路径
    jstring appDirPath = (jstring) env->CallStaticObjectMethod(mainActivityClass, getAppDirPathMethod, thiz);

    // 将 jstring 转换为 C++ 字符串
    const char *appDirPathCStr = env->GetStringUTFChars(appDirPath, NULL);
    std::string appDirPathStr(appDirPathCStr);
    env->ReleaseStringUTFChars(appDirPath, appDirPathCStr);
    size_t baseApkPos = appDirPathStr.find("base.apk");
    std::string fullLibPath ;
    auto obf =  MAKEOBF("lib/arm64/libotherso.so");
    if (baseApkPos != std::string::npos) {
        appDirPathStr = appDirPathStr.substr(0, baseApkPos);
        fullLibPath = appDirPathStr + (char*)obf;
    }else{
        fullLibPath = appDirPathStr + "/files/Data";
    }


    LOGI("%s",fullLibPath.c_str());


    MyLoader myLoader;
    myLoader.run(fullLibPath.c_str(),javaVM);
}


JNINativeMethod methods[] = {
        {"start", "()V", (void *) start}
};
extern "C"
int JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env;
    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) {
            return -1;
        }

    jclass clazz = env->FindClass("com/swdd/ezezez/Stub"); // Replace with your actual class path
    if (clazz == nullptr) {
            return -1;
    }
    if (env->RegisterNatives(clazz, methods, sizeof(methods) / sizeof(methods[0])) < 0) {
            return -1;
    }
    return JNI_VERSION_1_6;
}

extern "C"
JNIEXPORT jobject JNICALL
Java_com_swdd_ezeze_MainActivity_Check(JNIEnv *env, jobject thiz, jstring input) {
    // 获取传入的字符串
    const char *inputStr = env->GetStringUTFChars(input, NULL);

    // 找到MainActivity类
    jclass mainActivityClass = env->GetObjectClass(thiz);

    // 找到encrypt方法
    jmethodID encryptMethod = env->GetMethodID(mainActivityClass, "encrypt", "(Ljava/lang/String;)Ljava/lang/String;");
    if (encryptMethod == NULL) {
            env->ReleaseStringUTFChars(input, inputStr);
            return NULL; // encrypt方法未找到，直接返回
        }

    // 调用encrypt方法，传入input字符串并获取返回的加密结果
    jstring encryptedInput = (jstring) env->CallObjectMethod(thiz, encryptMethod, input);

    // 创建NSSCTF{faaaaakkkkkkeeee}字符串
    jstring originalString = env->NewStringUTF("NSSCTF{faaaaakkkkkkeeee}");

    // 调用encrypt方法，获取原始字符串的加密结果
    jstring encryptedOriginal = (jstring) env->CallObjectMethod(thiz, encryptMethod, originalString);

    // 将两个加密后的字符串转换为C字符串进行比较
    const char *encryptedInputStr = env->GetStringUTFChars(encryptedInput, NULL);
    const char *encryptedOriginalStr = env->GetStringUTFChars(encryptedOriginal, NULL);

    // 比较加密结果是否相同
    bool isEqual = (strcmp(encryptedInputStr, encryptedOriginalStr) == 0);

    // 释放所有字符串资源
    env->ReleaseStringUTFChars(input, inputStr);
    env->ReleaseStringUTFChars(encryptedInput, encryptedInputStr);
    env->ReleaseStringUTFChars(encryptedOriginal, encryptedOriginalStr);

    // 获取Boolean类并创建返回的Boolean对象
    jclass booleanClass = env->FindClass("java/lang/Boolean");
    jmethodID booleanConstructor = env->GetMethodID(booleanClass, "<init>", "(Z)V");
    jobject result = env->NewObject(booleanClass, booleanConstructor, isEqual);

    return result;
}