package com.yuzo.opengit.kotlin.ui.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.yuzo.opengit.kotlin.http.service.bean.Entrylist
import com.yuzo.opengit.kotlin.ui.repository.HomeRepository

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class HomeDataSourceFactory constructor(
    private val dataSource: MutableLiveData<HomeDataSource>,
    private val repository: HomeRepository
) :
    DataSource.Factory<Int, Entrylist>() {
    override fun create(): DataSource<Int, Entrylist> =
        HomeDataSource(repository).apply { dataSource.postValue(this) }
}