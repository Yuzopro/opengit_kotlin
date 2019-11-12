package com.yuzo.opengit.kotlin.ui.fragment

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import com.yuzo.lib.ui.adapter.BasePagedAdapter
import com.yuzo.lib.ui.fragment.BaseRefreshFragment
import com.yuzo.opengit.kotlin.http.service.bean.Event
import com.yuzo.opengit.kotlin.http.service.bean.Repo
import com.yuzo.opengit.kotlin.ui.AppViewModelProvider
import com.yuzo.opengit.kotlin.ui.adapter.EventAdapter
import com.yuzo.opengit.kotlin.ui.paging.EventDataSource
import com.yuzo.opengit.kotlin.ui.viewmodel.EventViewModel

/**
 * Author: yuzo
 * Date: 2019-10-11
 */
class EventFragment : BaseRefreshFragment<Event, EventAdapter, EventDataSource>(),
    BasePagedAdapter.OnItemClickListener<Event> {


    override var mAdapter: EventAdapter = EventAdapter()

    override val mViewModel: EventViewModel by viewModels {
        AppViewModelProvider.providerEventModel()
    }

    override fun initView() {
        super.initView()

        mAdapter.listener = this
    }

    override fun onDestroyView() {
        super.onDestroyView()

        mAdapter.listener = null
    }

    override fun OnItemClick(item: Event?, position: Int) {
//        val uri = Uri.parse(item?)
//        val intent = Intent(Intent.ACTION_VIEW, uri)
//        startActivity(intent)
    }
}