package com.yuzo.opengit.kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.yuzo.lib.log.v
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.ui.AppViewModelProvider
import com.yuzo.opengit.kotlin.ui.adapter.ListAdapter
import com.yuzo.opengit.kotlin.ui.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * Author: yuzo
 * Date: 2019-09-30
 */
class HomeFragment : Fragment() {
    private var mAdapter: ListAdapter? = null

    private val homeViewModel: HomeViewModel by viewModels {
        AppViewModelProvider.providerHomeModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = ListAdapter(context!!)
        rv_list.adapter = mAdapter

        swipe_refresh_layout.setOnRefreshListener {
            v(TAG, "onRefresh")
            swipe_refresh_layout.postDelayed({
                swipe_refresh_layout.isRefreshing = false
            }, 3000L)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /**
         * LiveData用LivePagedListBuilder生成
         * LivePagedListBuilder 构造方法需要 DataSource.Factory和PagedList.Config
         */
        homeViewModel.homes.observe(this, Observer {
            if (it != null) {
                mAdapter!!.submitList(it)
            }
        })
    }

    companion object {
        const val TAG = "HomeFragment"
    }
}