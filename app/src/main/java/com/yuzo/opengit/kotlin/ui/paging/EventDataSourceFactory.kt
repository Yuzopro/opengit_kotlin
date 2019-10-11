package com.yuzo.opengit.kotlin.ui.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.yuzo.opengit.kotlin.http.service.bean.Event
import com.yuzo.opengit.kotlin.http.service.bean.Repo
import com.yuzo.opengit.kotlin.ui.repository.EventRepository
import com.yuzo.opengit.kotlin.ui.repository.RepoRepository

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class EventDataSourceFactory constructor(
    private val dataSource: MutableLiveData<EventDataSource>,
    private val repository: EventRepository
) :
    DataSource.Factory<Int, Event>() {
    override fun create(): DataSource<Int, Event> =
        EventDataSource(repository).apply { dataSource.postValue(this) }
}