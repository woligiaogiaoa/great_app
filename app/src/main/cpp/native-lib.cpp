//
// Created by admin on 2021/1/6.
//


#include <string.h>
#include <jni.h>


/* This is a trivial JNI example where we use a native method
 * to return a new VM String. See the corresponding Java source
 * file located at:
 *
 *   hello-jni/app/src/main/java/com/example/hellojni/HelloJni.java
 */
extern "C"
JNIEXPORT jstring JNICALL
Java_com_jsn_great_MainActivity_stringFromJNI(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("hello,this time i m gonna chase my dream");
}