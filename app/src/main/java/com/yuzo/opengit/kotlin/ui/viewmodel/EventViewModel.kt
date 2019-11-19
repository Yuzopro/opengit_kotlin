package com.yuzo.opengit.kotlin.ui.viewmodel

import com.yuzo.lib.ui.viewmodel.BaseRefreshViewModel
import com.yuzo.opengit.kotlin.http.service.bean.Event
import com.yuzo.opengit.kotlin.ui.repository.EventRepository

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
class EventViewModel(repository: EventRepository) : BaseRefreshViewModel<Event>(repository) {

    companion object {
        private const val TAG: String = "EventViewModel"
    }

}