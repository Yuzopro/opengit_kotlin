package com.yuzo.opengit.kotlin.ui.repository

import com.yuzo.lib.http.NetworkScheduler
import com.yuzo.lib.http.ResponseObserver
import com.yuzo.opengit.kotlin.http.HttpClient2
import com.yuzo.opengit.kotlin.http.service.bean.Home

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class HomeRepository private constructor() {
    fun queryHomes(pageIndex: Int, perPage: Int, callback: ResponseObserver<Home>) {
        HttpClient2.getInstance().homeService
            .queryHomes(pageIndex, perPage)
            .compose(NetworkScheduler.compose())
            .subscribe(callback)
    }

    companion object {
        private const val TAG = "HomeRepository"

        @Volatile
        private var instance: HomeRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: HomeRepository().also { instance = it }
        }
    }
}