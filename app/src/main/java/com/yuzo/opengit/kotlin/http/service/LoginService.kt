package com.yuzo.opengit.kotlin.http.service

import com.yuzo.opengit.kotlin.http.service.bean.LoginRequest
import com.yuzo.opengit.kotlin.http.service.bean.Login
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
interface LoginService {
    @POST("authorizations")
    @Headers("Accept: application/json")
    fun authorizations(
        @Body request: LoginRequest
    ): Observable<Login>
}