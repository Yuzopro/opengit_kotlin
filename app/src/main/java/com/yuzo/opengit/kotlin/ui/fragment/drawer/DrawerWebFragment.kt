package com.yuzo.opengit.kotlin.ui.fragment.drawer

import android.os.Bundle
import com.yuzo.lib.ui.fragment.BaseWebFragment
import com.yuzo.opengit.kotlin.ui.DrawerCoordinateHelper

/**
 * Author: yuzo
 * Date: 2019-11-28
 */
class DrawerWebFragment : BaseWebFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(DrawerCoordinateHelper.getInstance())
    }
}