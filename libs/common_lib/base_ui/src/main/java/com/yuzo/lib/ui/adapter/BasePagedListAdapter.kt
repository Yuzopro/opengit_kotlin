package com.yuzo.lib.ui.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yuzo.lib.image.load
import com.yuzo.lib.tool.getMultiTime
import com.yuzo.lib.tool.transTimeStamp

/**
 * Author: yuzo
 * Date: 2019-10-10
 */
abstract class BasePagedListAdapter<T, B : ViewDataBinding, VH : BaseViewHolder<T, B>>(callback: DiffUtil.ItemCallback<T>) :
    PagedListAdapter<T, VH>(callback) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        holder.apply {
            bind(item!!)
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