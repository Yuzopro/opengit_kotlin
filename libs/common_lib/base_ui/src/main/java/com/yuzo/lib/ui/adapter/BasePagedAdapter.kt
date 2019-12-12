package com.yuzo.lib.ui.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yuzo.lib.image.load
import com.yuzo.lib.tool.getMultiTime
import com.yuzo.lib.tool.transTimeStamp

/**
 * Author: yuzo
 * Date: 2019-10-10
 */
abstract class BasePagedAdapter<T, VH : BaseViewHolder>(callback: DiffUtil.ItemCallback<T>) :
    PagedListAdapter<T, VH>(callback) {

    var listener: OnItemClickListener<T>? = null

    interface OnItemClickListener<T> {
        fun OnItemClick(item: T?, position: Int)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            val item = getItem(position)
            listener?.OnItemClick(item, position)
        }
    }
}

@BindingAdapter("image_url")
fun bindImageFromUrl(view: ImageView, url: String?) {
    url?.apply {
        load(view.context, view, url)
    }
}

@BindingAdapter("multi_time")
fun bindMultiTime(view: TextView, time: String?) {
    view.text = getMultiTime(transTimeStamp(time))
}