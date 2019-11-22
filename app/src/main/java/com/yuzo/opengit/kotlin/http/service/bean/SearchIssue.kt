package com.yuzo.opengit.kotlin.http.service.bean

/**
 * Author: yuzo
 * Date: 2019-11-22
 */
data class SearchIssue(
    val incomplete_results: Boolean,
    val items: List<Issue>,
    val total_count: Int
)