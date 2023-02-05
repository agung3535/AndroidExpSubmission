package com.example.moviesubmissionandroidexp.core.utils

object ApiKeysSource {
    init {
        System.loadLibrary("native-lib")
    }

    external fun apiKeys(id: Int): String
}