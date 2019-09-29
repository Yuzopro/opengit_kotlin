package com.yuzo.lib.http

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
const val TIME_OUT_SECONDS = 10

abstract class BaseHttpClient {
    abstract fun getBaseUrl(): String

    abstract fun getAuthInterceptor() : Interceptor

    abstract fun create(retrofit : Retrofit)

    fun init() {
        val okHttpClient = OkHttpClient()
            .newBuilder()
            .connectTimeout(TIME_OUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = when (BuildConfig.DEBUG) {
                    true -> HttpLoggingInterceptor.Level.BODY
                    false -> HttpLoggingInterceptor.Level.NONE
                }
            }).addInterceptor(getAuthInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        create(retrofit)
    }
}