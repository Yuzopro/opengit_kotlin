package com.yuzo.opengit.kotlin.ui.fragment

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.yuzo.lib.ui.adapter.BasePagedAdapter
import com.yuzo.lib.ui.fragment.BaseRefreshFragment
import com.yuzo.lib.ui.fragment.BaseWebFragment
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.http.service.bean.Event
import com.yuzo.opengit.kotlin.ui.adapter.EventAdapter
import com.yuzo.opengit.kotlin.ui.adapter.EventViewHolder
import com.yuzo.opengit.kotlin.ui.repository.EventRepository
import com.yuzo.opengit.kotlin.ui.viewmodel.EventViewModel

/**
 * Author: yuzo
 * Date: 2019-10-11
 */
class EventFragment : BaseRefreshFragment<Event, EventViewHolder, EventAdapter, EventViewModel>(),
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
        item?.apply {

            var url: String? = ""
            if (payload != null && payload.issue != null) {
                url = payload?.issue?.url
            } else if (repo != null) {
                url = repo.url
            }

            url?.apply {
                val bundle = Bundle()
                bundle.putString(BaseWebFragment.KEY_URL, this.replace("https://api.github.com/repos/", "https://github.com/"))
                nav().navigate(R.id.action_to_web, bundle)
            }

        }
    }

    companion object {
        private const val TAG = "EventFragment"
    }
}