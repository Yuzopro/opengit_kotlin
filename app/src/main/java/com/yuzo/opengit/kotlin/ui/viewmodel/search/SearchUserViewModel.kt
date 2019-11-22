package com.yuzo.opengit.kotlin.ui.viewmodel.search

import com.yuzo.lib.ui.viewmodel.BaseRefreshViewModel
import com.yuzo.opengit.kotlin.http.service.bean.Issue
import com.yuzo.opengit.kotlin.http.service.bean.User
import com.yuzo.opengit.kotlin.sp.directionSp
import com.yuzo.opengit.kotlin.sp.filterSp
import com.yuzo.opengit.kotlin.sp.sortSp
import com.yuzo.opengit.kotlin.sp.stateSp
import com.yuzo.opengit.kotlin.ui.repository.search.SearchIssueRepository
import com.yuzo.opengit.kotlin.ui.repository.search.SearchUserRepository

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
class SearchUserViewModel (val repository: SearchUserRepository) : BaseRefreshViewModel<User>(repository) {

    companion object {
        private const val TAG: String = "IssueViewModel"
    }

}