package com.yuzo.lib.ui.adapter.delegate

import android.view.View
import android.view.ViewGroup
import com.yuzo.lib.ui.adapter.BaseViewHolder


/**
 * Author: yuzo
 * Date: 2019-10-12
 */
interface ItemViewDelegate<T> {
    //获取Item类型
    fun getItemType(): Int

    //是否绑定数据
    fun isForViewType(item: T?, position: Int): Boolean

    //获取view
    fun getView(parent: ViewGroup): View

    //绑定数据
    fun convert(holder: BaseViewHolder, item: T?, position: Int)
}