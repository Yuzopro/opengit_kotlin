package com.yuzo.opengit.kotlin.ui.viewmodel

import com.yuzo.lib.ui.repository.BaseRepository
import com.yuzo.lib.ui.viewmodel.BaseRefreshViewModel
import com.yuzo.opengit.kotlin.http.service.bean.Repo

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
class RepoViewModel(repository: BaseRepository<Repo>) : BaseRefreshViewModel<Repo>(repository) {
}