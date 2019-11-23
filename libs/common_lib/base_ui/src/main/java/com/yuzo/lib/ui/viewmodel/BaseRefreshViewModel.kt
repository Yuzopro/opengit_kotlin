package com.yuzo.lib.ui.viewmodel

import android.util.Log.v
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import com.yuzo.lib.ui.repository.BaseRepository

/**
 * Author: yuzo
 * Date: 2019-11-14
 */
abstract class BaseRefreshViewModel<T>(repository: BaseRepository<T>) : BaseViewModel() {
    private val state = MutableLiveData<Map<String, Any>>()

    private val result = map(state, {
        repository.post(it, 20)
    })

    val lists = switchMap(result, { it.pagedList })!!
    val networkState = switchMap(result, { it.networkState })!!

    fun doAction(key: String, value: Any) {
        val map = HashMap<String, Any>()
        map.put(key, value)

        doAction(map)
    }

    fun doAction(map: HashMap<String, Any>) {
        var isSame = true
        if (state.value.isNullOrEmpty()) {
            isSame = false
        } else {
            val sourceMap = state.value
            if (sourceMap!!.size != map.size) {
                isSame = false
            } else {
                map.forEach{
                    val sourceValue = sourceMap[it.key]
                    if (sourceValue != it.value) {
                        isSame = false
                        return@forEach
                    }
                }
            }
        }

        if (!isSame) {
            state.value = map
        }
    }

    fun refresh() {
        result.value?.refresh?.invoke()
    }

    fun retry() {
        val listing = result?.value
        listing?.retry?.invoke()
    }
}