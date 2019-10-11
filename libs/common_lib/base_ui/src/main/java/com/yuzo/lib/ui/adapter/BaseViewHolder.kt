package com.yuzo.lib.ui.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Author: yuzo
 * Date: 2019-10-10
 */
abstract class BaseViewHolder<T, B : ViewDataBinding>(binding: B) :
    RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(item: T)
}