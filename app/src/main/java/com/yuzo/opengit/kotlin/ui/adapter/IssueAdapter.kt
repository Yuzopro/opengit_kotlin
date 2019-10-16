package com.yuzo.opengit.kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.yuzo.lib.ui.adapter.BaseMultiItemAdapter
import com.yuzo.lib.ui.adapter.BaseViewHolder
import com.yuzo.lib.ui.adapter.delegate.ItemViewDelegate
import com.yuzo.opengit.kotlin.databinding.ListItemIssueBinding
import com.yuzo.opengit.kotlin.http.service.bean.Issue

/**
 * Author: yuzo
 * Date: 2019-10-12
 */
class IssueAdapter : BaseMultiItemAdapter<Issue>(diffCallback) {
    init {
        addItemViewDelegate(IssueViewHolder())
    }

    private inner class IssueViewHolder : ItemViewDelegate<Issue> {
        private var mBinding: ListItemIssueBinding? = null

        override fun getItemType(): Int = VIEW_TYPE_ISSUE_ITEM

        override fun isForViewType(item: Issue?, position: Int): Boolean = item?.id != null

        override fun getView(parent: ViewGroup): View {
            mBinding = ListItemIssueBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return mBinding!!.root
        }

        override fun convert(holder: BaseViewHolder, item: Issue?, position: Int) {
            mBinding?.apply {
                this.item = item
                executePendingBindings()
            }
        }
    }

    companion object {
        private const val VIEW_TYPE_ISSUE_ITEM = 0

        private val diffCallback: DiffUtil.ItemCallback<Issue> =
            object : DiffUtil.ItemCallback<Issue>() {
                override fun areItemsTheSame(
                    oldItem: Issue,
                    newItem: Issue
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Issue,
                    newItem: Issue
                ): Boolean {
                    return oldItem.equals(newItem)
                }
            }
    }
}