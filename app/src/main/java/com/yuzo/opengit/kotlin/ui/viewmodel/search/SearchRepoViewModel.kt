package com.yuzo.opengit.kotlin.ui.viewmodel.search

import com.yuzo.lib.ui.viewmodel.BaseRefreshViewModel
import com.yuzo.opengit.kotlin.http.service.bean.Repo
import com.yuzo.opengit.kotlin.ui.repository.search.SearchRepoRepository

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
class SearchRepoViewModel(repository: SearchRepoRepository) : BaseRefreshViewModel<Repo>(repository) {
}