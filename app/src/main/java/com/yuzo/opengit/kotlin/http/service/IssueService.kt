package com.yuzo.opengit.kotlin.http.service

import com.yuzo.opengit.kotlin.http.service.bean.Issue
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Author: yuzo
 * Date: 2019-10-12
 */
interface IssueService {
    @GET("issues?since=2000-01-01T00:00:00Z")
    //&filter=all&state=all&sort=created&direction=desc
    fun queryIssues(
        @Query("filter") filter: String,
        @Query("state") state: String,
        @Query("sort") sort: String,
        @Query("direction") direction: String,
        @Query("page") pageIndex: Int,
        @Query("per_page") perPage: Int
    ): Call<List<Issue>>
}