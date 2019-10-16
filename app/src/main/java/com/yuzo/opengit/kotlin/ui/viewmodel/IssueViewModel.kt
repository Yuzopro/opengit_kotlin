package com.yuzo.opengit.kotlin.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.yuzo.lib.ui.viewmodel.BaseRefreshViewModel
import com.yuzo.opengit.kotlin.http.service.bean.Issue
import com.yuzo.opengit.kotlin.sp.directionSp
import com.yuzo.opengit.kotlin.sp.filterSp
import com.yuzo.opengit.kotlin.sp.sortSp
import com.yuzo.opengit.kotlin.sp.stateSp
import com.yuzo.opengit.kotlin.ui.paging.IssueDataSource
import com.yuzo.opengit.kotlin.ui.paging.IssueDataSourceFactory
import com.yuzo.opengit.kotlin.ui.repository.IssueRepository

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
class IssueViewModel constructor(repository: IssueRepository) :
    BaseRefreshViewModel<Issue, IssueDataSource>() {

    private val dataSourceFactory = IssueDataSourceFactory(
        dataSource, repository,
        filterSp,
        stateSp,
        sortSp,
        directionSp
    )

    override var lists: LiveData<PagedList<Issue>> =
        LivePagedListBuilder(dataSourceFactory, config).build()

    fun changeFilter(filter: String) {
        if (filterSp == filter) {
            return
        }
        filterSp = filter

        dataSourceFactory.changeSearchState(filter, stateSp, sortSp, directionSp)
        onRefresh()
    }

    fun changeState(state: String) {
        if (stateSp == state) {
            return
        }
        stateSp = state

        dataSourceFactory.changeSearchState(filterSp, state, sortSp, directionSp)
        onRefresh()
    }

    fun changeSort(sort: String) {
        if (sortSp == sort) {
            return
        }
        sortSp = sort

        dataSourceFactory.changeSearchState(filterSp, stateSp, sort, directionSp)
        onRefresh()
    }

    fun changeDirection(direction: String) {
        if (directionSp == direction) {
            return
        }
        directionSp = direction

        dataSourceFactory.changeSearchState(filterSp, stateSp, sortSp, direction)
        onRefresh()
    }

    companion object {
        private const val TAG: String = "IssueViewModel"
    }

}