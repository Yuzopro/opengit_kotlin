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
const val TIME_OUT_SECONDS = 20

abstract class BaseHttpClient {
    abstract fun getBaseUrl(): String

    abstract fun create(retrofit: Retrofit)

    fun init() {
        val builder = OkHttpClient()
            .newBuilder()
            .connectTimeout(TIME_OUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .sslSocketFactory(HttpUtil.createSSLSocketFactory()!!)
            .hostnameVerifier(HttpUtil.TrustAllHostnameVerifier())
            .connectionSpecs(HttpUtil.getConnectionSpec())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = when (BuildConfig.DEBUG) {
                    true -> HttpLoggingInterceptor.Level.BODY
                    false -> HttpLoggingInterceptor.Level.NONE
                }
            })

        getAuthInterceptor()?.apply {
            builder.addInterceptor(this)
        }

        val okHttpClient = builder.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        create(retrofit)
    }

    open fun getAuthInterceptor(): Interceptor? {
        return null
    }
}