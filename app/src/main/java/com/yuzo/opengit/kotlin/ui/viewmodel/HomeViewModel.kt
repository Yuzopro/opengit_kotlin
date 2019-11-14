package com.yuzo.opengit.kotlin.ui.viewmodel

import com.yuzo.lib.ui.repository.BaseRepository
import com.yuzo.lib.ui.viewmodel.BaseRefreshViewModel
import com.yuzo.opengit.kotlin.http.service.bean.Entrylist

/**
 * Author: yuzo
 * Date: 2019-11-14
 */
class HomeViewModel(repository: BaseRepository<Entrylist>) : BaseRefreshViewModel<Entrylist>(repository) {
}