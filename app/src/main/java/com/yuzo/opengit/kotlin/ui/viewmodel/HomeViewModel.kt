package com.yuzo.opengit.kotlin.ui.viewmodel

import com.yuzo.lib.ui.viewmodel.BaseRefreshViewModel
import com.yuzo.opengit.kotlin.http.service.bean.Entrylist
import com.yuzo.opengit.kotlin.ui.repository.HomeRepository

/**
 * Author: yuzo
 * Date: 2019-11-14
 */
class HomeViewModel(repository: HomeRepository) : BaseRefreshViewModel<Entrylist>(repository) {
}