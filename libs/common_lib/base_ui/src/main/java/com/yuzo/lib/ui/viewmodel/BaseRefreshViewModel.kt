package com.yuzo.lib.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import com.yuzo.lib.ui.repository.BaseRepository

/**
 * Author: yuzo
 * Date: 2019-11-14
 */
abstract class BaseRefreshViewModel<T>(repository: BaseRepository<T>) : BaseViewModel() {
    private val state = MutableLiveData<Int>()

    private val result = map(state, {
        repository.post(it, 20)
    })

    val lists = switchMap(result, { it.pagedList })!!
    val networkState = switchMap(result, { it.networkState })!!

    fun doAction(subreddit: Int): Boolean {
        if (state.value == subreddit) {
            return false
        }
        state.value = subreddit
        return true
    }

    fun refresh() {
        result.value?.refresh?.invoke()
    }

    fun retry() {
        val listing = result?.value
        listing?.retry?.invoke()
    }
}