package com.yuzo.opengit.kotlin.http

import com.yuzo.lib.http.BaseHttpClient
import com.yuzo.opengit.kotlin.http.service.HomeService
import retrofit2.Retrofit

/**
 * Author: yuzo
 * Date: 2019-09-29
 */
class HttpClient2 : BaseHttpClient() {
    lateinit var homeService: HomeService

    override fun create(retrofit: Retrofit) {
        homeService = retrofit.create(HomeService::class.java)
    }

    override fun getBaseUrl(): String = BASE_URL

    companion object {
        const val BASE_URL = "https://timeline-merger-ms.juejin.im/"

        @Volatile
        private var instance: HttpClient2? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance
                ?: HttpClient2().also { instance = it }
        }
    }
}