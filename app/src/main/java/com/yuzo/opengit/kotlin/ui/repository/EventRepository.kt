package com.yuzo.opengit.kotlin.ui.repository

import com.google.gson.Gson
import com.yuzo.lib.http.NetworkScheduler
import com.yuzo.lib.http.ResponseObserver
import com.yuzo.opengit.kotlin.http.HttpClient
import com.yuzo.opengit.kotlin.http.service.bean.Event
import com.yuzo.opengit.kotlin.http.service.bean.User
import com.yuzo.opengit.kotlin.sp.userSp

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class EventRepository private constructor() {
    private var user: User? = null

    init {
        user = Gson().fromJson(userSp, User::class.java)
    }

    fun queryEvents(pageIndex: Int, perPage: Int, callback: ResponseObserver<List<Event>>) {
        HttpClient.getInstance().userService
            .queryReceivedEvents(user!!.login, pageIndex, perPage)
            .compose(NetworkScheduler.compose())
            .subscribe(callback)
    }

    companion object {
        private const val TAG = "EventRepository"

        @Volatile
        private var instance: EventRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: EventRepository().also { instance = it }
        }
    }
}