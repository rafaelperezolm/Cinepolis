package com.rafaelperezolm.cinepolis.data.remote

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class CinepolisClient {

    fun getClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder
            .connectTimeout(45, TimeUnit.SECONDS)
            .readTimeout(45, TimeUnit.SECONDS)
            .writeTimeout(45, TimeUnit.SECONDS)
            .build()
        return okHttpClientBuilder.build()
    }

}