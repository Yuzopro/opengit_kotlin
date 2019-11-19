package com.yuzo.opengit.kotlin.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.yuzo.lib.ui.adapter.BasePagedAdapter
import com.yuzo.lib.ui.fragment.BaseLazyFragment
import com.yuzo.lib.ui.fragment.BaseRefreshFragment
import com.yuzo.opengit.kotlin.http.service.bean.Entrylist
import com.yuzo.opengit.kotlin.ui.adapter.HomeAdapter
import com.yuzo.opengit.kotlin.ui.repository.HomeRepository
import com.yuzo.opengit.kotlin.ui.viewmodel.HomeViewModel

/**
 * Author: yuzo
 * Date: 2019-09-30
 */
class HomeFragment : BaseRefreshFragment<Entrylist, HomeAdapter, HomeViewModel>(),
    BasePagedAdapter.OnItemClickListener<Entrylist> {

    override var mAdapter: HomeAdapter = HomeAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.v(TAG, "onActivityCreated")
    }

    override fun initView() {
        super.initView()

        mAdapter.listener = this
    }

    override fun onDestroyView() {
        super.onDestroyView()

        mAdapter.listener = null
    }

    override fun getViewModel(): HomeViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return HomeViewModel(HomeRepository()) as T
            }
        })[HomeViewModel::class.java]
    }

    override fun OnItemClick(item: Entrylist?, position: Int) {
        val uri = Uri.parse(item?.originalUrl)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    companion object {
        private const val TAG = "HomeFragment"
    }
}