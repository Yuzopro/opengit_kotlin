package com.yuzo.opengit.kotlin.http.service.bean

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("token")
    val token: String,
    @SerializedName("url")
    val url: String
)