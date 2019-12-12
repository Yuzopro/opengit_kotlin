package com.yuzo.opengit.kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import com.yuzo.lib.image.load
import com.yuzo.lib.tool.getMultiTime
import com.yuzo.lib.tool.transTimeStamp
import com.yuzo.lib.ui.adapter.BasePagedAdapter
import com.yuzo.lib.ui.adapter.BaseViewHolder
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.http.service.bean.Issue

/**
 * Author: yuzo
 * Date: 2019-10-12
 */
class IssueAdapter : BasePagedAdapter<Issue, IssueViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_issue, parent, false)
        return IssueViewHolder(view)
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.binds(getItem(position)!!, position)
    }

    companion object {
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

class IssueViewHolder(view: View) : BaseViewHolder(view) {
    private val ivAvatar: ImageView = view.findViewById(R.id.iv_issue_avatar)
    private val tvOwnerName: TextView = view.findViewById(R.id.tv_issue_user_name)
    private val tvDate: TextView = view.findViewById(R.id.tv_issue_update_time)
    private val tvName: TextView = view.findViewById(R.id.tv_issue_name)
    private val tvDesc: TextView = view.findViewById(R.id.tv_issue_desc)
    private val tvComment: TextView = view.findViewById(R.id.tv_issue_comment)
    private val tvLabel: TextView = view.findViewById(R.id.tv_issue_label)
    private val tvNumber: TextView = view.findViewById(R.id.tv_issue_number)

    fun binds(data: Issue, position: Int) {
        load(ivAvatar.context, ivAvatar, data.user?.avatarUrl)
        tvOwnerName.text = data.user?.login
        tvDate.text = getMultiTime(transTimeStamp(data.updated_at))
        tvName.text = data.title
        tvDesc.text = data.body ?: "(No description, website, or topics provided.)"
        tvComment.text = "${data.comments}"
        tvLabel.text = "${data.labels?.size}"
        tvNumber.text = "${data.number}"
    }
}