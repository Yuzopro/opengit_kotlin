package com.yuzo.lib.ui.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * Author: yuzo
 * Date: 2019-11-14
 */
data class Listing<T>(
    val pagedList: LiveData<PagedList<T>>,
    val networkState: LiveData<NetworkState>,
    val refresh: () -> Unit,
    val retry: () -> Unit
)