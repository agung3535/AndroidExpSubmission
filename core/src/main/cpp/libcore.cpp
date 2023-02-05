//
// Created by Putra on 23/11/22.
//

#include <jni.h>
#include <string>

std::string getData(int x) {
    std::string app_secret = "Null";

    if (x == 1) app_secret = "80b5fd4d416c198a2917e73df9fec5d9"; //The first key that you want to protect against decompilation
    if (x == 2) app_secret = "abcdefg";   //The second key that you want to protect against decompilation

    // The number of parameters to be protected can be increased.

    return app_secret;
}


extern "C" jstring
Java_com_example_moviesubmissionandroidexp_core_utils_ApiKeysSource_apiKeys(
        JNIEnv *env,
        jobject /* this */,
        jint id) {
    std::string app_secret = "Null";
    app_secret = getData(id);
    return env->NewStringUTF(app_secret.c_str());
}


