package com.yuzo.opengit.kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.yuzo.lib.ui.adapter.BaseMultiItemAdapter
import com.yuzo.lib.ui.adapter.BaseViewHolder
import com.yuzo.lib.ui.adapter.delegate.ItemViewDelegate
import com.yuzo.opengit.kotlin.databinding.ListItemUserBinding
import com.yuzo.opengit.kotlin.http.service.bean.User

/**
 * Author: yuzo
 * Date: 2019-10-12
 */
class UserAdapter : BaseMultiItemAdapter<User>(diffCallback) {
    init {
        addItemViewDelegate(UserViewHolder())
    }

    private inner class UserViewHolder : ItemViewDelegate<User> {
        private var mBinding: ListItemUserBinding? = null

        override fun getItemType(): Int = VIEW_TYPE_ISSUE_ITEM

        override fun isForViewType(item: User?, position: Int): Boolean = item?.id != null

        override fun getView(parent: ViewGroup): View {
            mBinding = ListItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return mBinding!!.root
        }

        override fun convert(holder: BaseViewHolder, item: User?, position: Int) {
            mBinding?.apply {
                this.item = item
                executePendingBindings()
            }
        }
    }

    companion object {
        private const val VIEW_TYPE_ISSUE_ITEM = 0

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