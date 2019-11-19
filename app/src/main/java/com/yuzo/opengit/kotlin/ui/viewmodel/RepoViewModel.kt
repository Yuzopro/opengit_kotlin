package com.yuzo.opengit.kotlin.ui.viewmodel

import com.yuzo.lib.ui.viewmodel.BaseRefreshViewModel
import com.yuzo.opengit.kotlin.http.service.bean.Repo
import com.yuzo.opengit.kotlin.ui.repository.RepoRepository

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
class RepoViewModel(repository: RepoRepository) : BaseRefreshViewModel<Repo>(repository) {
}