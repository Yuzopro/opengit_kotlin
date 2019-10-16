package com.yuzo.opengit.kotlin.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.lxj.xpopup.XPopup
import com.yuzo.lib.ui.fragment.BaseRefreshFragment
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.http.service.bean.Issue
import com.yuzo.opengit.kotlin.sp.directionSp
import com.yuzo.opengit.kotlin.sp.filterSp
import com.yuzo.opengit.kotlin.sp.sortSp
import com.yuzo.opengit.kotlin.sp.stateSp
import com.yuzo.opengit.kotlin.ui.AppViewModelProvider
import com.yuzo.opengit.kotlin.ui.adapter.IssueAdapter
import com.yuzo.opengit.kotlin.ui.paging.IssueDataSource
import com.yuzo.opengit.kotlin.ui.viewmodel.IssueViewModel

/**
 * Author: yuzo
 * Date: 2019-10-12
 */
class IssueFragment : BaseRefreshFragment<Issue, IssueAdapter, IssueDataSource>() {
    private val filter = arrayOf("assigned", "created", "mentioned", "subscribed", "all")
    private val state = arrayOf("open", "closed", "all")
    private val sort = arrayOf("created", "updated", "comments")
    private val direction = arrayOf("asc", "desc")

    override var mAdapter: IssueAdapter = IssueAdapter()

    override val mViewModel: IssueViewModel by viewModels {
        AppViewModelProvider.providerIssueModel()
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

                if (state == FILTER) {
                    mViewModel.changeFilter(text)
                } else if (state == STATE) {
                    mViewModel.changeState(text)
                } else if (state == SORT) {
                    mViewModel.changeSort(text)
                } else if (state == DIRECTION) {
                    mViewModel.changeDirection(text)
                }
            }
            .show()
    }

    companion object {
        private const val FILTER = 0
        private const val STATE = 1
        private const val SORT = 2
        private const val DIRECTION = 3
    }
}