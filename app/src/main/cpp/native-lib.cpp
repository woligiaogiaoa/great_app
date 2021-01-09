//
// Created by admin on 2021/1/6.
//


#include <string.h>
#include <jni.h>
#include <android/log.h>
#define TAG "nativeLib"
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__)
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)


/* This is a trivial JNI example where we use a native method
 * to return a new VM String. See the corresponding Java source
 * file located at:
 *
 *   hello-jni/app/src/main/java/com/example/hellojni/HelloJni.java
 */
extern "C"
JNIEXPORT jstring JNICALL
Java_com_jsn_great_MainActivity_stringFromJNI(JNIEnv *env, jobject thiz) {
    LOGE("log message from nativeLib error level");
    LOGD("log message from nativeLib debug level");
    return env->NewStringUTF("hello,this time i m gonna chase my dream");
}