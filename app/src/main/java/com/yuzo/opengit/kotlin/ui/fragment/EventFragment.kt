package com.yuzo.opengit.kotlin.ui.fragment

import androidx.fragment.app.viewModels
import com.yuzo.lib.ui.fragment.BaseRefreshFragment
import com.yuzo.opengit.kotlin.http.service.bean.Event
import com.yuzo.opengit.kotlin.ui.AppViewModelProvider
import com.yuzo.opengit.kotlin.ui.adapter.EventAdapter
import com.yuzo.opengit.kotlin.ui.paging.EventDataSource
import com.yuzo.opengit.kotlin.ui.viewmodel.EventViewModel

/**
 * Author: yuzo
 * Date: 2019-10-11
 */
class EventFragment : BaseRefreshFragment<Event, EventAdapter, EventDataSource>() {
    override var mAdapter: EventAdapter = EventAdapter()

    override val mViewModel: EventViewModel by viewModels {
        AppViewModelProvider.providerEventModel()
    }
}