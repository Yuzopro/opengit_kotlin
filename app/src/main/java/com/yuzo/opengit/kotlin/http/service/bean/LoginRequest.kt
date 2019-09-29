package com.yuzo.opengit.kotlin.http.service.bean

import com.google.gson.annotations.SerializedName
import com.yuzo.opengit.kotlin.BuildConfig

data class LoginRequest(
    val scopes: List<String>,
    val note: String,
    @SerializedName("client_id")
    val clientId: String,
    @SerializedName("client_secret")
    val clientSecret: String
) {
    companion object {

        fun generate(): LoginRequest {
            return LoginRequest(
                scopes = listOf("user", "repo", "gist", "notifications"),
                note = BuildConfig.APPLICATION_ID,
                clientId = BuildConfig.CLIENT_ID,
                clientSecret = BuildConfig.CLIENT_SECRET
            )
        }
    }
}