package com.yuzo.opengit.kotlin.ui.repository

import com.google.gson.Gson
import com.yuzo.lib.ui.paging.AbsDataSourceFactory
import com.yuzo.lib.ui.repository.AbsRepository
import com.yuzo.opengit.kotlin.http.service.bean.Event
import com.yuzo.opengit.kotlin.http.service.bean.User
import com.yuzo.opengit.kotlin.sp.userSp
import com.yuzo.opengit.kotlin.ui.paging.EventDataSource
import com.yuzo.opengit.kotlin.ui.paging.EventDataSourceFactory

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class EventRepository : AbsRepository<Event, EventDataSource>() {

    private var user: User? = null

    init {
        user = Gson().fromJson(userSp, User::class.java)
    }

    override fun getDataSourceFactory(): AbsDataSourceFactory<Event, EventDataSource> = EventDataSourceFactory(user?.login)

    companion object {
        private const val TAG = "EventRepository"
    }
}