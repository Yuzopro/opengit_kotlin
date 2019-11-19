package com.yuzo.opengit.kotlin.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.yuzo.lib.ui.adapter.BasePagedAdapter
import com.yuzo.lib.ui.fragment.BaseRefreshFragment
import com.yuzo.lib.ui.viewmodel.BaseRefreshViewModel
import com.yuzo.opengit.kotlin.http.service.bean.Event
import com.yuzo.opengit.kotlin.ui.adapter.EventAdapter
import com.yuzo.opengit.kotlin.ui.repository.EventRepository
import com.yuzo.opengit.kotlin.ui.viewmodel.EventViewModel

/**
 * Author: yuzo
 * Date: 2019-10-11
 */
class EventFragment : BaseRefreshFragment<Event, EventAdapter, EventViewModel>(),
    BasePagedAdapter.OnItemClickListener<Event> {


    override var mAdapter: EventAdapter = EventAdapter()

    override fun initView() {
        super.initView()

        mAdapter.listener = this
    }

    override fun onDestroyView() {
        super.onDestroyView()

        mAdapter.listener = null
    }

    override fun getViewModel(): EventViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return EventViewModel(EventRepository()) as T
            }
        })[EventViewModel::class.java]
    }

    override fun OnItemClick(item: Event?, position: Int) {
//        val uri = Uri.parse(item?)
//        val intent = Intent(Intent.ACTION_VIEW, uri)
//        startActivity(intent)
    }

    companion object {
        private const val TAG = "EventFragment"
    }
}