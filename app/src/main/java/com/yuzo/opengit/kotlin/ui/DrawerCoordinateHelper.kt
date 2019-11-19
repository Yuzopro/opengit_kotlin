package com.yuzo.opengit.kotlin.ui

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import java.util.*

/**
 * Author: yuzo
 * Date: 2019-11-15
 */
class DrawerCoordinateHelper private constructor() : DefaultLifecycleObserver {
    var listener: OnDrawerLockListener? = null

    private var pages: MutableList<String> = ArrayList()


    interface OnDrawerLockListener {
        fun onDrawerLock(isUnLock: Boolean)
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        pages.add(owner.javaClass.simpleName)
        listener?.onDrawerLock(pages.size == 0)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        pages.remove(owner.javaClass.simpleName)
        listener?.onDrawerLock(pages.size == 0)
    }

    companion object {
        private const val TAG = "DrawerCoordinateHelper"

        @Volatile
        private var instance: DrawerCoordinateHelper? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: DrawerCoordinateHelper().also { instance = it }
        }
    }
}