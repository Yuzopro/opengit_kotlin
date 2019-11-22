package com.yuzo.opengit.kotlin.http.service

import com.yuzo.opengit.kotlin.http.service.bean.SearchIssue
import com.yuzo.opengit.kotlin.http.service.bean.SearchRepo
import com.yuzo.opengit.kotlin.http.service.bean.SearchUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Author: yuzo
 * Date: 2019-11-22
 */
interface SearchService {
    @GET("search/repositories")
    fun searchRepo(
        @Query("q") q: String?,
        @Query("page") pageIndex: Int,
        @Query("per_page") perPage: Int
    ): Call<SearchRepo>

    @GET("search/users")
    fun searchUser(
        @Query("q") q: String?,
        @Query("page") pageIndex: Int,
        @Query("per_page") perPage: Int
    ): Call<SearchUser>

    @GET("search/issues")
    fun searchIssue(
        @Query("q") q: String?,
        @Query("page") pageIndex: Int,
        @Query("per_page") perPage: Int
    ): Call<SearchIssue>
}