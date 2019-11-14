package com.yuzo.opengit.kotlin.ui.paging

import com.yuzo.lib.ui.paging.AbsDataSourceFactory
import com.yuzo.opengit.kotlin.http.service.bean.Event

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class EventDataSourceFactory(val name: String?) : AbsDataSourceFactory<Event, EventDataSource>() {
    override fun getDataSource(): EventDataSource = EventDataSource(name)

    companion object {
        private const val TAG = "EventDataSourceFactory"
    }
}