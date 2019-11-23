package com.yuzo.lib.ui.repository

/**
 * Author: yuzo
 * Date: 2019-11-14
 */
interface BaseRepository<T> {
    fun post(params : Map<String, Any>, pageSize : Int) : Listing<T>
}