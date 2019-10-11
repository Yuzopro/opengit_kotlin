package com.yuzo.opengit.kotlin.http.service

import com.yuzo.opengit.kotlin.http.service.bean.Home
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Author: yuzo
 * Date: 2019-10-11
 */
interface HomeService {
    @GET("v1/get_tag_entry?src=web&tagId=5a96291f6fb9a0535b535438&sort=rankIndex")
    fun queryHomes(@Query("page") page: Int, @Query("pageSize") pageSize: Int): Observable<Home>
}