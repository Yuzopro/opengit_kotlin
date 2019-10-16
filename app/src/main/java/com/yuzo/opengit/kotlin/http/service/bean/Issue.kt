package com.yuzo.opengit.kotlin.http.service.bean

/**
 * Author: yuzo
 * Date: 2019-10-12
 */
data class Issue(
    val assignee: Any? = null,
    val assignees: List<Any>? = null,
    val author_association: String? = null,
    val body: String? = null,
    val closed_at: Any? = null,
    val comments: Int? = null,
    val comments_url: String? = null,
    val created_at: String? = null,
    val events_url: String? = null,
    val html_url: String? = null,
    val id: Int? = null,
    val labels: List<Any>? = null,
    val labels_url: String? = null,
    val locked: Boolean? = null,
    val milestone: Any? = null,
    val node_id: String? = null,
    val number: Int? = null,
    val repository_url: String? = null,
    val state: String? = null,
    val title: String? = null,
    val updated_at: String? = null,
    val url: String? = null,
    val user: User? = null
)
