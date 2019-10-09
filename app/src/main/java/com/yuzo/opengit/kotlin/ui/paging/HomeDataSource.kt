package com.yuzo.opengit.kotlin.ui.paging

import androidx.paging.PositionalDataSource
import com.yuzo.lib.log.v

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class HomeDataSource : PositionalDataSource<String>() {
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<String>) {
        val requestedLoadSize = params.requestedLoadSize
        v(TAG, "loadInitial requestedLoadSize is ${requestedLoadSize}")
        callback.onResult(getSubList(0, requestedLoadSize), 0)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<String>) {
        v(TAG, "loadRange startPosition is ${params.startPosition}@loadSize is ${params.loadSize}")
        callback.onResult(getSubList(params.startPosition, params.loadSize))
    }

    fun getSubList(start: Int, end: Int): List<String> {
        val strings = ArrayList<String>()
        for (i in 0 until end) {
            strings.add("$start -->$i")
        }
        return strings
    }

    companion object {
        const val TAG = "HomeDataSource"
    }
}