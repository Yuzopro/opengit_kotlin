package com.yuzo.lib.ui.adapter

import android.view.View
import android.view.ViewGroup
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
abstract class BasePagedAdapter<T>(callback: DiffUtil.ItemCallback<T>) :
    PagedListAdapter<T, BaseViewHolder>(callback) {

    abstract fun getView(parent: ViewGroup, viewType: Int): View

    abstract fun setItem(holder: BaseViewHolder, item: T?, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(getView(parent, viewType))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = getItem(position)
        holder.apply {
            setItem(this, item, position)
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