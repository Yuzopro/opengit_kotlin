package com.yuzo.lib.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.yuzo.lib.ui.adapter.delegate.ItemViewDelegate
import com.yuzo.lib.ui.adapter.delegate.ItemViewDelegateManager


/**
 * Author: yuzo
 * Date: 2019-10-12
 */
abstract class BaseMultiItemAdapter<T>(callback: DiffUtil.ItemCallback<T>) :
    BasePagedAdapter<T>(callback) {

    protected var mItemViewDelegateManager: ItemViewDelegateManager<T>? = null

    init {
        mItemViewDelegateManager = ItemViewDelegateManager()
    }

    override fun getItemViewType(position: Int): Int {
        return mItemViewDelegateManager!!.getItemViewType(getItem(position), position)
    }

    override fun getView(parent: ViewGroup, viewType: Int): View {
        val itemViewDelegate = mItemViewDelegateManager!!.getItemViewDelegate(viewType)
        val view = itemViewDelegate.getView(parent)
        return view
    }

    override fun setItem(holder: BaseViewHolder, item: T?, position: Int) {
        mItemViewDelegateManager!!.convert(holder, getItem(position), position);
    }

    fun addItemViewDelegate(itemViewDelegate: ItemViewDelegate<T>): BaseMultiItemAdapter<T> {
        mItemViewDelegateManager!!.addDelegate(itemViewDelegate)
        return this
    }

}