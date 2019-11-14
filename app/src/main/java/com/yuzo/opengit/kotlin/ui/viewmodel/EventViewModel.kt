package com.yuzo.opengit.kotlin.ui.viewmodel

import com.yuzo.lib.ui.repository.BaseRepository
import com.yuzo.lib.ui.viewmodel.BaseRefreshViewModel
import com.yuzo.opengit.kotlin.http.service.bean.Event

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
class EventViewModel(repository: BaseRepository<Event>) : BaseRefreshViewModel<Event>(repository) {

    companion object {
        private const val TAG: String = "RepoViewModel"
    }

}