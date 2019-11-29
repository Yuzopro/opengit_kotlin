package com.yuzo.opengit.kotlin.ui.fragment.search

import com.yuzo.lib.ui.adapter.BasePagedAdapter
import com.yuzo.lib.ui.fragment.BaseRefreshFragment
import com.yuzo.lib.ui.viewmodel.BaseRefreshViewModel

/**
 * Author: yuzo
 * Date: 2019-11-22
 */
abstract class SearchItemFragment<T, A : BasePagedAdapter<T>, V : BaseRefreshViewModel<T>> :
    BaseRefreshFragment<T, A, V>(), BasePagedAdapter.OnItemClickListener<T> {

    override fun isFirstRun(): Boolean = false

    override fun initView() {
        super.initView()

        mAdapter.listener = this

        showException(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        mAdapter.listener = null
    }

    fun doSearch(text: String) {
        mViewModel.doAction("q", text)
    }
}