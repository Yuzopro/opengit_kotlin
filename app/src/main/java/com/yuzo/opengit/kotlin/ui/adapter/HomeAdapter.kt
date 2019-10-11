package com.yuzo.opengit.kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yuzo.lib.ui.adapter.BasePagedListAdapter
import com.yuzo.lib.ui.adapter.BaseViewHolder
import com.yuzo.opengit.kotlin.databinding.ListItemHomeBinding
import com.yuzo.opengit.kotlin.http.service.bean.Entrylist
import com.yuzo.opengit.kotlin.http.service.bean.Tag

/**
 * Author: yuzo
 * Date: 2019-10-11
 */
class HomeAdapter :
    BasePagedListAdapter<Entrylist, ListItemHomeBinding, HomeViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            ListItemHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    companion object {
        private val diffCallback: DiffUtil.ItemCallback<Entrylist> =
            object : DiffUtil.ItemCallback<Entrylist>() {

                override fun areItemsTheSame(
                    oldItem: Entrylist,
                    newItem: Entrylist
                ): Boolean {
                    return oldItem.objectId == newItem.objectId
                }

                override fun areContentsTheSame(
                    oldItem: Entrylist,
                    newItem: Entrylist
                ): Boolean {
                    return oldItem.equals(newItem)
                }
            }
    }
}

class HomeViewHolder(private val binding: ListItemHomeBinding) :
    BaseViewHolder<Entrylist, ListItemHomeBinding>(binding) {
    override fun bind(item: Entrylist) {
        binding.apply {
            this.item = item
            executePendingBindings()
        }
    }
}

@BindingAdapter("home_tag")
fun bindHomeTag(view: TextView, tags: List<Tag>?) {
    view.text = getTags(tags)
}

private fun getTags(tags: List<Tag>?): String {
    var tag = ""
    if (tags != null && tags.isNotEmpty()) {
        var size = tags.size
        if (size > 2) {
            size = 2
        }
        for (i in 0 until size) {
            tag += tags[i].title + "/"
        }
    }
    return tag
}