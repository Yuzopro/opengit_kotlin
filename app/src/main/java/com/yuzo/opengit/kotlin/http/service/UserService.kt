package com.yuzo.opengit.kotlin.http.service

import com.yuzo.opengit.kotlin.http.service.bean.Event
import com.yuzo.opengit.kotlin.http.service.bean.Repo
import com.yuzo.opengit.kotlin.http.service.bean.User
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Author: yuzo
 * Date: 2019-09-29
 */
interface UserService {
    @GET("user")
    fun fetchUserOwner(): Observable<User>

    @GET("users/{username}/repos?")
    fun queryRepos(
        @Path("username") username: String?,
        @Query("page") pageIndex: Int,
        @Query("per_page") perPage: Int,
        @Query("sort") sort: String
    ): Call<List<Repo>>

    @GET("users/{username}/received_events?")
    fun queryReceivedEvents(
        @Path("username") username: String?,
        @Query("page") pageIndex: Int,
        @Query("per_page") perPage: Int
    ): Call<List<Event>>
}