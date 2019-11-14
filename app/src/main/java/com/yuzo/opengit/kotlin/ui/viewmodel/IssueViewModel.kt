package com.yuzo.opengit.kotlin.ui.viewmodel

import com.yuzo.lib.ui.repository.BaseRepository
import com.yuzo.lib.ui.viewmodel.BaseRefreshViewModel
import com.yuzo.opengit.kotlin.http.service.bean.Issue
import com.yuzo.opengit.kotlin.sp.directionSp
import com.yuzo.opengit.kotlin.sp.filterSp
import com.yuzo.opengit.kotlin.sp.sortSp
import com.yuzo.opengit.kotlin.sp.stateSp

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
class IssueViewModel (repository: BaseRepository<Issue>) : BaseRefreshViewModel<Issue>(repository) {
//    override val mDataSourceFactory: DataSource.Factory<Int, Issue> = IssueDataSourceFactory(
//        dataSource, repository,
//        filterSp,
//        stateSp,
//        sortSp,
//        directionSp
//    )

    fun changeFilter(filter: String) {
        if (filterSp == filter) {
            return
        }
        filterSp = filter

//        if (mDataSourceFactory is IssueDataSourceFactory) {
//            mDataSourceFactory.changeSearchState(filter, stateSp, sortSp, directionSp)
//        }
//        onRefresh()
    }

    fun changeState(state: String) {
        if (stateSp == state) {
            return
        }
        stateSp = state

//        if (mDataSourceFactory is IssueDataSourceFactory) {
//            mDataSourceFactory.changeSearchState(filterSp, state, sortSp, directionSp)
//        }
//        onRefresh()
    }

    fun changeSort(sort: String) {
        if (sortSp == sort) {
            return
        }
        sortSp = sort

//        if (mDataSourceFactory is IssueDataSourceFactory) {
//            mDataSourceFactory.changeSearchState(filterSp, stateSp, sort, directionSp)
//        }
//        onRefresh()
    }

    fun changeDirection(direction: String) {
        if (directionSp == direction) {
            return
        }
        directionSp = direction

//        if (mDataSourceFactory is IssueDataSourceFactory) {
//            mDataSourceFactory.changeSearchState(filterSp, stateSp, sortSp, direction)
//        }
//        onRefresh()
    }

    companion object {
        private const val TAG: String = "IssueViewModel"
    }

}