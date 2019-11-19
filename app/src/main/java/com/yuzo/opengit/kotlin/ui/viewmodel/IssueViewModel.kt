package com.yuzo.opengit.kotlin.ui.viewmodel

import com.yuzo.lib.ui.viewmodel.BaseRefreshViewModel
import com.yuzo.opengit.kotlin.http.service.bean.Issue
import com.yuzo.opengit.kotlin.sp.directionSp
import com.yuzo.opengit.kotlin.sp.filterSp
import com.yuzo.opengit.kotlin.sp.sortSp
import com.yuzo.opengit.kotlin.sp.stateSp
import com.yuzo.opengit.kotlin.ui.repository.IssueRepository

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
class IssueViewModel (val repository: IssueRepository) : BaseRefreshViewModel<Issue>(repository) {

    fun changeFilter(filter: String) {
        if (filterSp == filter) {
            return
        }
        filterSp = filter
        refresh()
    }

    fun changeState(state: String) {
        if (stateSp == state) {
            return
        }
        stateSp = state
        refresh()
    }

    fun changeSort(sort: String) {
        if (sortSp == sort) {
            return
        }
        sortSp = sort
        refresh()
    }

    fun changeDirection(direction: String) {
        if (directionSp == direction) {
            return
        }
        directionSp = direction
        refresh()
    }

    companion object {
        private const val TAG: String = "IssueViewModel"
    }

}