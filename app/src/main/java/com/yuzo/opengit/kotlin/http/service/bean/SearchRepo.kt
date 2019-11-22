package com.yuzo.opengit.kotlin.http.service.bean

/**
 * Author: yuzo
 * Date: 2019-11-22
 */
data class SearchRepo(
    val incomplete_results: Boolean,
    val items: List<Repo>,
    val total_count: Int
)