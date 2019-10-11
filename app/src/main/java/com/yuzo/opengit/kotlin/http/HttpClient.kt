package com.yuzo.opengit.kotlin.http

import com.yuzo.lib.http.BaseHttpClient
import com.yuzo.opengit.kotlin.http.interceptor.BasicAuthInterceptor
import com.yuzo.opengit.kotlin.http.service.LoginService
import com.yuzo.opengit.kotlin.http.service.UserService
import okhttp3.Interceptor
import retrofit2.Retrofit

/**
 * Author: yuzo
 * Date: 2019-09-29
 */
class HttpClient : BaseHttpClient() {
    lateinit var loginService: LoginService
    lateinit var userService: UserService

    override fun create(retrofit: Retrofit) {
        loginService = retrofit.create(LoginService::class.java)
        userService = retrofit.create(UserService::class.java)
    }

    override fun getAuthInterceptor(): Interceptor? = BasicAuthInterceptor()

    override fun getBaseUrl(): String = BASE_URL

    companion object {
        const val BASE_URL = "https://api.github.com/"

        @Volatile
        private var instance: HttpClient? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance
                ?: HttpClient().also { instance = it }
        }
    }
}