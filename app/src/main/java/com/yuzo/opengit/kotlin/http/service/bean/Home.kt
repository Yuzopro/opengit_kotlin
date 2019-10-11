package com.yuzo.opengit.kotlin.http.service.bean

/**
 * Author: yuzo
 * Date: 2019-10-11
 */
data class Home(
    val d: D,
    val m: String,
    val s: Int
)

data class D(
    val entrylist: List<Entrylist>,
    val total: Int
)

data class Entrylist(
    val author: String,
    val autoPass: Boolean,
    val buildTime: Double,
    val category: Category,
    val checkStatus: Boolean,
    val collectionCount: Int,
    val commentsCount: Int,
    val content: String,
    val createdAt: String,
    val english: Boolean,
    val entryView: String,
    val gfw: Boolean,
    val hot: Boolean,
    val hotIndex: Double,
    val isCollected: Boolean,
    val isEvent: Boolean,
    val lastCommentTime: String,
    val ngxCachedTime: Int,
    val objectId: String,
    val original: Boolean,
    val originalUrl: String,
    val rankIndex: Double,
    val screenshot: String,
    val subscribersCount: Int,
    val summaryInfo: String,
    val tags: List<Tag>,
    val title: String,
    val type: String,
    val updatedAt: String,
    val user: HomeUser,
    val userRankIndex: Double,
    val verifyCreatedAt: String,
    val verifyStatus: Boolean,
    val viewsCount: Int
)

data class Tag(
    val id: String,
    val ngxCached: Boolean,
    val ngxCachedTime: Int,
    val title: String
)

data class Category(
    val id: String,
    val name: String,
    val ngxCached: Boolean,
    val ngxCachedTime: Int,
    val title: String
)

data class HomeUser(
    val avatarLarge: String,
    val collectedEntriesCount: Int,
    val community: Community,
    val company: String,
    val followeesCount: Int,
    val followersCount: Int,
    val isAuthor: Boolean,
    val jobTitle: String,
    val level: Int,
    val ngxCached: Boolean,
    val ngxCachedTime: Int,
    val objectId: String,
    val postedEntriesCount: Int,
    val postedPostsCount: Int,
    val role: String,
    val subscribedTagsCount: Int,
    val totalCollectionsCount: Int,
    val totalCommentsCount: Int,
    val username: String,
    val viewedEntriesCount: Int
)

data class Community(
    val wechat: Wechat
)

data class Wechat(
    val avatarLarge: String
)