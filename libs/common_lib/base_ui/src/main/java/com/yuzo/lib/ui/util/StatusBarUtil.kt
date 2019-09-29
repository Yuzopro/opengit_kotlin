package com.yuzo.lib.ui.util

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import com.yuzo.lib.tool.getStatusBarHeight
import com.yuzo.lib.ui.StatusBarView

/**
 * Author: yuzo
 * Date: 2019-09-29
 */
fun setColorNoTranslucent(activity: Activity, @ColorInt color: Int) {
    setColor(activity, color, 0)
}

fun setColor(activity: Activity, @ColorInt color: Int, statusBarAlpha: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        activity.window.statusBarColor = calculateStatusColor(color, statusBarAlpha)
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        val decorView = activity.window.decorView as ViewGroup
        val count = decorView.childCount
        if (count > 0 && decorView.getChildAt(count - 1) is StatusBarView) {
            decorView.getChildAt(count - 1)
                .setBackgroundColor(calculateStatusColor(color, statusBarAlpha))
        } else {
            val statusView = createStatusBarView(activity, color, statusBarAlpha)
            decorView.addView(statusView)
        }
        setRootView(activity)
    }
}

private fun calculateStatusColor(@ColorInt color: Int, alpha: Int): Int {
    val a = 1 - alpha / 255f
    var red = color shr 16 and 0xff
    var green = color shr 8 and 0xff
    var blue = color and 0xff
    red = (red * a + 0.5).toInt()
    green = (green * a + 0.5).toInt()
    blue = (blue * a + 0.5).toInt()
    return 0xff shl 24 or (red shl 16) or (green shl 8) or blue
}

private fun createStatusBarView(
    activity: Activity, @ColorInt color: Int,
    alpha: Int
): StatusBarView {
    // 绘制一个和状态栏一样高的矩形
    val statusBarView = StatusBarView(activity)
    val params =
        LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity))
    statusBarView.layoutParams = params
    statusBarView.setBackgroundColor(calculateStatusColor(color, alpha))
    return statusBarView
}

private fun setRootView(activity: Activity) {
    val rootView =
        (activity.findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup
    rootView.fitsSystemWindows = true
    rootView.clipToPadding = true
}