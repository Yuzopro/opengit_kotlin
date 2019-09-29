package com.yuzo.opengit.kotlin.http.service

import com.yuzo.opengit.kotlin.http.service.bean.UserResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Author: yuzo
 * Date: 2019-09-29
 */
interface UserService {
    @GET("user")
    fun fetchUserOwner(): Observable<UserResponse>
}