#include <jni.h>
#include <string>
#include <android/log.h>

#define TAG "SWDD"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)

//NSSCTF{H0w_M4g1cal_l1nke3r}
const unsigned char storedBytes[] = { 0xb1,0xac,0xac,0xbc,0xab,0xb9,0x84,0xb7,0xcf,0x88,0xa0,0xb2,0xcb,0x98,0xce,0x9c,0x9e,0x93,0xa0,0x93,0xce,0x91,0x94,0x9a,0xcc,0x8d,0x82,};
static jobject Check(JNIEnv *env, jobject thiz, jstring input) {
    const char* str = env->GetStringUTFChars(input, nullptr);
    if (str == nullptr) {
        return nullptr;
    }


    unsigned char xorKey = 0xff;
    size_t inputLength = strlen(str);
    unsigned char* xoredInput = new unsigned char[inputLength + 1];
    for (size_t i = 0; i < inputLength; ++i) {
        xoredInput[i] = str[i] ^ xorKey;
    }
    xoredInput[inputLength] = '\0';


    bool isEqual = false;
    if(inputLength == 27){
        isEqual = (memcmp(xoredInput, storedBytes, inputLength) == 0);
    }

    jclass booleanClass = env->FindClass("java/lang/Boolean");
    jmethodID booleanConstructor = env->GetMethodID(booleanClass, "<init>", "(Z)V");
    jobject result = env->NewObject(booleanClass, booleanConstructor, isEqual);


    env->ReleaseStringUTFChars(input, str);
    delete[] xoredInput;

    return result;
}

static jobject Check2(JNIEnv *env, jobject thiz, jstring input) {
    const char *inputStr = env->GetStringUTFChars(input, NULL);
    //LOGI("Input received2: %s", inputStr);

    // Perform some checks
    bool isEqual = false;
    if (strcmp(inputStr, "NSSCTF{aa5ba5c564ba65c4654ca4d654c}") == 0) {
        isEqual = true;
    }
    jclass booleanClass = env->FindClass("java/lang/Boolean");
    jmethodID booleanConstructor = env->GetMethodID(booleanClass, "<init>", "(Z)V");
    jobject result = env->NewObject(booleanClass, booleanConstructor, isEqual);


    env->ReleaseStringUTFChars(input, inputStr);

    return result;
}
JNINativeMethod methods[] = {
        {"Check", "(Ljava/lang/String;)Ljava/lang/Boolean;", (void *) Check2}
};

void __attribute__((constructor())) Gen() {
    LOGI("你好");
    for (int i = 0; i < sizeof(methods) / sizeof(methods[0]); ++i) {
        if (strcmp(methods[i].name, "Check") == 0) {
            methods[i].fnPtr = (void *) Check;
            break;
        }
    }
}


extern "C"
int JNI_OnLoad(JavaVM *vm, void *reserved) {
    LOGI("hello");
    JNIEnv *env;
    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        return -1;
    }

    jclass clazz = env->FindClass("com/swdd/ezezez/MainActivity"); // Replace with your actual class path
    if (clazz == nullptr) {
        return -1;
    }
    if (env->RegisterNatives(clazz, methods, sizeof(methods) / sizeof(methods[0])) < 0) {
        return -1;
    }
    return JNI_VERSION_1_6;
}