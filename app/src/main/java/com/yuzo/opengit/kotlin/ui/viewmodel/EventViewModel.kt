package com.yuzo.opengit.kotlin.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.yuzo.lib.ui.viewmodel.BaseRefreshViewModel
import com.yuzo.opengit.kotlin.http.service.bean.Event
import com.yuzo.opengit.kotlin.ui.paging.EventDataSource
import com.yuzo.opengit.kotlin.ui.paging.EventDataSourceFactory
import com.yuzo.opengit.kotlin.ui.repository.EventRepository

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
class EventViewModel constructor(repository: EventRepository) :
    BaseRefreshViewModel<Event, EventDataSource>() {

    override var lists: LiveData<PagedList<Event>> =
        LivePagedListBuilder(EventDataSourceFactory(dataSource, repository), config)
            .build()

    companion object {
        private const val TAG: String = "RepoViewModel"
    }

}