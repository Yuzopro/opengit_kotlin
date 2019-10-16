package com.yuzo.opengit.kotlin.ui.repository

import com.yuzo.lib.http.NetworkScheduler
import com.yuzo.lib.http.ResponseObserver
import com.yuzo.opengit.kotlin.http.HttpClient
import com.yuzo.opengit.kotlin.http.service.bean.Issue

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class IssueRepository private constructor() {
    fun queryIssues(
        filter: String,
        state: String,
        sort: String,
        direction: String,
        pageIndex: Int,
        perPage: Int,
        callback: ResponseObserver<List<Issue>>
    ) {
        HttpClient.getInstance().issueService
            .queryIssues(filter, state, sort, direction, pageIndex, perPage)
            .compose(NetworkScheduler.compose())
            .subscribe(callback)
    }

    companion object {
        private const val TAG = "IssueRepository"

        @Volatile
        private var instance: IssueRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: IssueRepository().also { instance = it }
        }
    }
}