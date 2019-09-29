package com.yuzo.lib.tool

import android.app.Activity
import android.app.KeyguardManager
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.Rect
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Surface
import java.lang.reflect.Field

/**
 * Author: yuzo
 * Date: 2019-09-25
 */
fun getScreenWidth(context: Context): Int {
    return context.resources.displayMetrics.widthPixels
}

fun getScreenHeight(context: Context): Int {
    return context.resources.displayMetrics.heightPixels
}

fun setLandscape(activity: Activity) {
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
}

fun setPortrait(activity: Activity) {
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
}

fun isLandscape(context: Context): Boolean {
    return context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
}

fun isPortrait(context: Context): Boolean {
    return context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
}

fun getScreenRotation(activity: Activity): Int {
    when (activity.windowManager.defaultDisplay.rotation) {
        Surface.ROTATION_0 -> return 0
        Surface.ROTATION_90 -> return 90
        Surface.ROTATION_180 -> return 180
        Surface.ROTATION_270 -> return 270
        else -> return 0
    }
}

fun dpToPx(context: Context, dpValue: Float): Int {
    try {
        val scale = context.getResources().getDisplayMetrics().density
        return (dpValue * scale + 0.5f).toInt()
    } catch (ignore: Exception) {
    }

    return -1
}

fun pxToDp(context: Context, pxValue: Float): Int {
    try {
        val scale = context.getResources().getDisplayMetrics().density
        return (pxValue / scale + 0.5f).toInt()
    } catch (ignore: Exception) {
    }

    return -1
}

fun pxToSp(context: Context, pxValue: Float): Int {
    val fontScale = context.getResources().getDisplayMetrics().scaledDensity
    return (pxValue / fontScale + 0.5f).toInt()
}

fun spToPx(context: Context, spValue: Float): Int {
    val fontScale = context.getResources().getDisplayMetrics().scaledDensity
    return (spValue * fontScale + 0.5f).toInt()
}

fun applyDimension(unit: Int, value: Float, metrics: DisplayMetrics): Float {
    when (unit) {
        TypedValue.COMPLEX_UNIT_PX -> return value
        TypedValue.COMPLEX_UNIT_DIP -> return value * metrics.density
        TypedValue.COMPLEX_UNIT_SP -> return value * metrics.scaledDensity
        TypedValue.COMPLEX_UNIT_PT -> return value * metrics.xdpi * (1.0f / 72)
        TypedValue.COMPLEX_UNIT_IN -> return value * metrics.xdpi
        TypedValue.COMPLEX_UNIT_MM -> return value * metrics.xdpi * (1.0f / 25.4f)
    }
    return 0f
}

fun captureWithStatusBar(activity: Activity): Bitmap {
    val view = activity.window.decorView
    view.isDrawingCacheEnabled = true
    view.buildDrawingCache()
    val bmp = view.drawingCache
    val dm = DisplayMetrics()
    activity.windowManager.defaultDisplay.getMetrics(dm)
    val ret = Bitmap.createBitmap(bmp, 0, 0, dm.widthPixels, dm.heightPixels)
    view.destroyDrawingCache()
    return ret
}

fun captureWithoutStatusBar(activity: Activity): Bitmap {
    val view = activity.window.decorView
    view.isDrawingCacheEnabled = true
    view.buildDrawingCache()
    val bmp = view.drawingCache
    val statusBarHeight = getStatusBarHeight(activity)
    val dm = DisplayMetrics()
    activity.windowManager.defaultDisplay.getMetrics(dm)
    val ret = Bitmap.createBitmap(
        bmp,
        0,
        statusBarHeight,
        dm.widthPixels,
        dm.heightPixels - statusBarHeight
    )
    view.destroyDrawingCache()
    return ret
}

fun isScreenLock(context: Context): Boolean {
    val km = context
        .getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
    return km.inKeyguardRestrictedInputMode()
}

fun getNavigationBarHeight(activity: Activity): Int {
    val d = activity.windowManager.defaultDisplay

    val outSize = Point()
    d.getRealSize(outSize)

    val realHeight = outSize.y

    d.getSize(outSize)

    val displayHeight = outSize.y
    var hasNavigationBar: Boolean
    val rs = activity.resources
    val id = rs.getIdentifier("config_showNavigationBar", "bool", "android")
    try {
        hasNavigationBar = rs.getBoolean(id)
    } catch (e: Throwable) {
        hasNavigationBar = false
    }

    return if (hasNavigationBar) {
        Math.max(0, realHeight - displayHeight)
    } else 0
}
