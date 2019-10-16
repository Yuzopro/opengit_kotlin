package com.yuzo.lib.ui.adapter.delegate

import android.util.SparseArray
import com.yuzo.lib.ui.adapter.BaseViewHolder


/**
 * Author: yuzo
 * Date: 2019-10-12
 */
class ItemViewDelegateManager<T> {
    var delegates: SparseArray<ItemViewDelegate<T>> = SparseArray()

    fun getItemViewDelegateCount(): Int {
        return delegates.size()
    }

    fun addDelegate(delegate: ItemViewDelegate<T>): ItemViewDelegateManager<T> {
        val viewType = delegate.getItemType()
        //判断之前是否已经添加过
        if (delegates.get(viewType) != null) {
            throw IllegalArgumentException(
                "An ItemViewDelegate is already registered for the viewType = "
                        + viewType
                        + ". Already registered ItemViewDelegate is "
                        + delegates.get(viewType)
            )
        }
        delegates.put(viewType, delegate)
        return this
    }

    fun removeDelegate(delegate: ItemViewDelegate<T>): ItemViewDelegateManager<T> {
        val indexToRemove = delegates.indexOfValue(delegate)

        if (indexToRemove >= 0) {
            delegates.removeAt(indexToRemove)
        }
        return this
    }

    fun removeDelegate(itemType: Int): ItemViewDelegateManager<T> {
        val indexToRemove = delegates.indexOfKey(itemType)
        if (indexToRemove >= 0) {
            delegates.removeAt(indexToRemove)
        }
        return this
    }

    //根据position 来获取类型
    fun getItemViewType(item: T?, position: Int): Int {
        val delegatesCount = delegates.size()
        for (i in 0 until delegatesCount) {
            val delegate = delegates.valueAt(i)
            if (delegate.isForViewType(item, position)) {
                return delegates.keyAt(i)
            }
        }
        //没有添加过抛出异常
        throw IllegalArgumentException(
            "No ItemViewDelegate added that matches position=$position"
        )
    }

    //根据类型来绑定数据
    fun convert(holder: BaseViewHolder, item: T?, position: Int) {
        val delegatesCount = delegates.size()
        for (i in 0 until delegatesCount) {
            val delegate = delegates.valueAt(i)
            if (delegate.isForViewType(item, position)) {
                delegate.convert(holder, item, position)
                return
            }
        }
        throw IllegalArgumentException(
            "No ItemViewDelegateManager added that matches position=$position in data source"
        )
    }

    //根据type获取getItemViewDelegate对象
    fun getItemViewDelegate(viewType: Int): ItemViewDelegate<T> {
        return delegates.get(viewType)
    }

    //获取指定ItemViewDelegate对应的类型
    fun getItemViewType(itemViewDelegate: ItemViewDelegate<T>): Int {
        return delegates.indexOfValue(itemViewDelegate)
    }
}