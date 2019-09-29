package com.yuzo.opengit.kotlin.http.interceptor

import android.util.Base64
import com.yuzo.opengit.kotlin.sp.accountSp
import com.yuzo.opengit.kotlin.sp.passwordSp
import com.yuzo.opengit.kotlin.sp.tokenSp
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
class BasicAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val accessToken = getAuthorization()

        if (!accessToken.isEmpty()) {
            val url = request.url().toString()
            request = request.newBuilder()
                .addHeader("Authorization", accessToken)
                .url(url)
                .build()
        }

        return chain.proceed(request)
    }

    private fun getAuthorization(): String {
        val accessToken = tokenSp
        val username = accountSp
        val password = passwordSp

        if (accessToken.isBlank()) {
            val basicIsEmpty = username.isBlank() || password.isBlank()
            return if (basicIsEmpty) {
                ""
            } else {
                "$username:$password".let {
                    "basic " + Base64.encodeToString(it.toByteArray(), Base64.NO_WRAP)
                }
            }
        }
        return "token $accessToken"
    }
}