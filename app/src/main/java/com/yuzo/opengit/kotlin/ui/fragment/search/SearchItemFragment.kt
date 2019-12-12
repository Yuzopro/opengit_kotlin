package com.yuzo.opengit.kotlin.ui.fragment.search

import androidx.paging.PagedListAdapter
import com.yuzo.lib.ui.adapter.BasePagedAdapter
import com.yuzo.lib.ui.adapter.BaseViewHolder
import com.yuzo.lib.ui.fragment.BaseRefreshFragment
import com.yuzo.lib.ui.viewmodel.BaseRefreshViewModel

/**
 * Author: yuzo
 * Date: 2019-11-22
 */
abstract class SearchItemFragment<T, VH : BaseViewHolder, A : PagedListAdapter<T, VH>, V : BaseRefreshViewModel<T>> :
    BaseRefreshFragment<T, VH, A, V>(), BasePagedAdapter.OnItemClickListener<T> {

    override fun isFirstRun(): Boolean = false

    override fun initView() {
        super.initView()

//        mAdapter.listener = this

        showException(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()

//        mAdapter.listener = null
    }

    fun doSearch(text: String) {
        mViewModel.doAction("q", text)
    }
}