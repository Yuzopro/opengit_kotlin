package com.yuzo.opengit.kotlin.ui.fragment

import androidx.fragment.app.viewModels
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
class HomeFragment : BaseRefreshFragment<Entrylist, HomeAdapter, HomeDataSource>() {

    override var mAdapter: HomeAdapter = HomeAdapter()

    override val mViewModel: HomeViewModel by viewModels {
        AppViewModelProvider.providerHomeModel()
    }

    companion object {
        private const val TAG = "HomeFragment"
    }
}