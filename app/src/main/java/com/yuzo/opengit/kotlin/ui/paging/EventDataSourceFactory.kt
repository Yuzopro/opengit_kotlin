package com.yuzo.opengit.kotlin.ui.paging

import com.yuzo.lib.ui.paging.AbsDataSourceFactory
import com.yuzo.opengit.kotlin.http.service.bean.Event

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class EventDataSourceFactory(val name: String?, params : Map<String, Any>) : AbsDataSourceFactory<Event, EventDataSource>(params) {
    override fun getDataSource(params : Map<String, Any>): EventDataSource = EventDataSource(name, params)

    companion object {
        private const val TAG = "EventDataSourceFactory"
    }
}