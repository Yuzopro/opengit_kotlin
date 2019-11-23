package com.yuzo.opengit.kotlin.ui.fragment.search

import com.yuzo.lib.ui.adapter.BasePagedAdapter
import com.yuzo.lib.ui.fragment.BaseRefreshFragment
import com.yuzo.lib.ui.viewmodel.BaseRefreshViewModel

/**
 * Author: yuzo
 * Date: 2019-11-22
 */
abstract class SearchItemFragment<T, A : BasePagedAdapter<T>, V : BaseRefreshViewModel<T>> :
    BaseRefreshFragment<T, A, V>() {

    override fun isFirstRun(): Boolean = false

    override fun initView() {
        super.initView()

        showException(false)
    }

    fun doSearch(text: String) {
        mViewModel.doAction("q", text)
    }
}