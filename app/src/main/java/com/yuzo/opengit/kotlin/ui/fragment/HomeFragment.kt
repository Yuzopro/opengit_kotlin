package com.yuzo.opengit.kotlin.ui.fragment

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import com.yuzo.lib.ui.adapter.BasePagedAdapter
import com.yuzo.lib.ui.fragment.BaseRefreshFragment
import com.yuzo.opengit.kotlin.http.service.bean.Entrylist
import com.yuzo.opengit.kotlin.ui.AppViewModelProvider
import com.yuzo.opengit.kotlin.ui.adapter.HomeAdapter
import com.yuzo.opengit.kotlin.ui.paging.HomeDataSource
import com.yuzo.opengit.kotlin.ui.viewmodel.HomeViewModel


/**
 * Author: yuzo
 * Date: 2019-09-30
 */
class HomeFragment : BaseRefreshFragment<Entrylist, HomeAdapter, HomeDataSource>(),
    BasePagedAdapter.OnItemClickListener<Entrylist> {

    override var mAdapter: HomeAdapter = HomeAdapter()

    override val mViewModel: HomeViewModel by viewModels {
        AppViewModelProvider.providerHomeModel()
    }

    override fun initView() {
        super.initView()

        mAdapter.listener = this
    }

    override fun onDestroyView() {
        super.onDestroyView()

        mAdapter.listener = null
    }

    companion object {
        private const val TAG = "HomeFragment"
    }

    override fun OnItemClick(item: Entrylist?, position: Int) {
        val uri = Uri.parse(item?.originalUrl)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}