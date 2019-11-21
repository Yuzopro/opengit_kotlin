package com.yuzo.opengit.kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.lxj.xpopup.XPopup
import com.yuzo.lib.ui.activity.BaseWebActivity
import com.yuzo.lib.ui.adapter.BasePagedAdapter
import com.yuzo.lib.ui.fragment.BaseRefreshFragment
import com.yuzo.lib.ui.fragment.BaseWebFragment
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.http.service.bean.Issue
import com.yuzo.opengit.kotlin.sp.directionSp
import com.yuzo.opengit.kotlin.sp.filterSp
import com.yuzo.opengit.kotlin.sp.sortSp
import com.yuzo.opengit.kotlin.sp.stateSp
import com.yuzo.opengit.kotlin.ui.adapter.IssueAdapter
import com.yuzo.opengit.kotlin.ui.repository.IssueRepository
import com.yuzo.opengit.kotlin.ui.viewmodel.IssueViewModel

/**
 * Author: yuzo
 * Date: 2019-10-12
 */
class IssueFragment : BaseRefreshFragment<Issue, IssueAdapter, IssueViewModel>(),
    BasePagedAdapter.OnItemClickListener<Issue> {

    private val filter = arrayOf("assigned", "created", "mentioned", "subscribed", "all")
    private val state = arrayOf("open", "closed", "all")
    private val sort = arrayOf("created", "updated", "comments")
    private val direction = arrayOf("asc", "desc")

    override var mAdapter: IssueAdapter = IssueAdapter()

    override fun initView() {
        super.initView()

        mAdapter.listener = this
    }

    override fun onDestroyView() {
        super.onDestroyView()

        mAdapter.listener = null
    }

    override fun getViewModel(): IssueViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return IssueViewModel(IssueRepository()) as T
            }
        })[IssueViewModel::class.java]
    }

    override fun OnItemClick(item: Issue?, position: Int) {
        val bundle = Bundle()
        bundle.putString(BaseWebFragment.KEY_URL, item?.html_url!!)
        nav().navigate(R.id.action_to_web, bundle)
    }

    override fun getFixView(parent: ViewGroup): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_issue_header, null, false)

        val tvFilter = view.findViewById<TextView>(R.id.tv_issue_filter)
        tvFilter.text = filterSp

        val tvState = view.findViewById<TextView>(R.id.tv_issue_state)
        tvState.text = stateSp

        val tvSort = view.findViewById<TextView>(R.id.tv_issue_sort)
        tvSort.text = sortSp

        val tvDirection = view.findViewById<TextView>(R.id.tv_issue_direction)
        tvDirection.text = directionSp

        tvFilter.setOnClickListener { showPopupMenu(it as TextView, filter, FILTER) }
        tvState.setOnClickListener { showPopupMenu(it as TextView, state, STATE) }
        tvSort.setOnClickListener { showPopupMenu(it as TextView, sort, SORT) }
        tvDirection.setOnClickListener { showPopupMenu(it as TextView, direction, DIRECTION) }

        return view
    }

    private fun showPopupMenu(view: TextView, array: Array<String>, state: Int) {
        XPopup.Builder(view.context)
            .atView(view)
            .asAttachList(array, null) { position, text ->
                view.text = text

                when (state) {
                    FILTER -> {
                        mViewModel.changeFilter(text)
                    }
                    STATE -> {
                        mViewModel.changeState(text)
                    }
                    SORT -> {
                        mViewModel.changeSort(text)
                    }
                    DIRECTION -> {
                        mViewModel.changeDirection(text)
                    }
                }
            }
            .show()
    }

    companion object {
        private const val TAG = "IssueFragment"

        private const val FILTER = 0
        private const val STATE = 1
        private const val SORT = 2
        private const val DIRECTION = 3
    }
}