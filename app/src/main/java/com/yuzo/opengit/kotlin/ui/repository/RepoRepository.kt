package com.yuzo.opengit.kotlin.ui.repository

import com.google.gson.Gson
import com.yuzo.lib.http.NetworkScheduler
import com.yuzo.lib.http.ResponseObserver
import com.yuzo.opengit.kotlin.http.HttpClient
import com.yuzo.opengit.kotlin.http.service.bean.Repo
import com.yuzo.opengit.kotlin.http.service.bean.User
import com.yuzo.opengit.kotlin.sp.userSp

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class RepoRepository private constructor() {
    private var user: User? = null

    init {
        user = Gson().fromJson(userSp, User::class.java)
    }

    fun queryRepos(pageIndex: Int, perPage: Int, callback: ResponseObserver<List<Repo>>) {
        HttpClient.getInstance().userService
            .queryRepos(user!!.login, pageIndex, perPage, "pushed")
            .compose(NetworkScheduler.compose())
            .subscribe(callback)
    }

    companion object {
        private const val TAG = "RepoRepository"

        @Volatile
        private var instance: RepoRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: RepoRepository().also { instance = it }
        }
    }
}