package com.yuzo.opengit.kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import com.yuzo.lib.image.load
import com.yuzo.lib.ui.adapter.BasePagedAdapter
import com.yuzo.lib.ui.adapter.BaseViewHolder
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.http.service.bean.User

/**
 * Author: yuzo
 * Date: 2019-10-12
 */
class UserAdapter : BasePagedAdapter<User, UserViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.binds(getItem(position)!!, position)
    }

    companion object {
        private val diffCallback: DiffUtil.ItemCallback<User> =
            object : DiffUtil.ItemCallback<User>() {
                override fun areItemsTheSame(
                    oldItem: User,
                    newItem: User
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: User,
                    newItem: User
                ): Boolean {
                    return oldItem.equals(newItem)
                }
            }
    }
}

class UserViewHolder(view: View) : BaseViewHolder(view) {
    private val ivAvatar: ImageView = view.findViewById(R.id.iv_repo_avatar)
    private val tvOwnerName: TextView = view.findViewById(R.id.tv_repo_user_name)

    fun binds(data: User, position: Int) {
        load(ivAvatar.context, ivAvatar, data.avatarUrl)
        tvOwnerName.text = data.login
    }
}