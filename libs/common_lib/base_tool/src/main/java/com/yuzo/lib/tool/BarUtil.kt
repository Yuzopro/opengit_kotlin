package com.yuzo.lib.tool

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.view.WindowManager
import java.lang.reflect.InvocationTargetException
import java.util.*

/**
 * Author: yuzo
 * Date: 2019-09-27
 */
fun setTransparentStatusBar(activity: Activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        //透明状态栏
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //透明导航栏
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
    }
}

fun hideStatusBar(activity: Activity) {
    activity.requestWindowFeature(Window.FEATURE_NO_TITLE)
    activity.window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN
    )
}

fun getStatusBarHeight(context: Context): Int {
    var result = 0
    val resourceId = context.resources
        .getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = context.resources.getDimensionPixelSize(resourceId)
    }
    return result
}

fun isStatusBarExists(activity: Activity): Boolean {
    val params = activity.window.attributes
    return params.flags and WindowManager.LayoutParams.FLAG_FULLSCREEN != WindowManager.LayoutParams.FLAG_FULLSCREEN
}

fun getActionBarHeight(activity: Activity): Int {
    val tv = TypedValue()
    return if (activity.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
        TypedValue.complexToDimensionPixelSize(tv.data, activity.resources.displayMetrics)
    } else 0
}

fun statusBarLightMode(activity: Activity): Int {
    var result = 0
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        if (MIUISetStatusBarLightMode(activity, true)) {
            result = 1
        } else if (isFlymeOS()) {
            result = 2
            setStatusBarDarkIcon(activity, true, true)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decorView = activity.window.decorView
            val flags = decorView.systemUiVisibility
            activity.window.decorView.systemUiVisibility =
                flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            result = 3
        }
    }
    return result
}

fun MIUISetStatusBarLightMode(activity: Activity, dark: Boolean): Boolean {
    var result = false
    val window = activity.window
    if (window != null) {
        val clazz = window.javaClass
        try {
            var darkModeFlag = 0
            val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
            val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
            darkModeFlag = field.getInt(layoutParams)
            val extraFlagField = clazz.getMethod(
                "setExtraFlags",
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType
            )
            if (dark) {
                extraFlagField.invoke(window, darkModeFlag, darkModeFlag)//状态栏透明且黑色字体
            } else {
                extraFlagField.invoke(window, 0, darkModeFlag)//清除黑色字体
            }
            result = true

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //开发版 7.7.13 及以后版本采用了系统API，旧方法无效但不会报错，所以两个方式都要加上
                if (dark) {
                    val decorView = activity.window.decorView
                    val flags = decorView.systemUiVisibility
                    activity.window.decorView.systemUiVisibility =
                        flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    //                        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                } else {
                    activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                }
            }
        } catch (e: Exception) {

        }

    }
    return result
}

private fun isFlymeOS(): Boolean {
    return getMeizuFlymeOSFlag().toLowerCase(Locale.getDefault()).contains("flyme")
}

private fun getMeizuFlymeOSFlag(): String {
    return getSystemProperty("ro.build.display.id", "")
}

private fun getSystemProperty(key: String, defaultValue: String): String {
    try {
        val clz = Class.forName("android.os.SystemProperties")
        val get = clz.getMethod("get", String::class.java, String::class.java)
        return get.invoke(clz, key, defaultValue) as String
    } catch (e: Exception) {
    }

    return defaultValue
}

private fun setStatusBarDarkIcon(activity: Activity, dark: Boolean, flag: Boolean) {
    try {
        val mSetStatusBarDarkIcon = Activity::class.java.getMethod(
            "setStatusBarDarkIcon",
            Boolean::class.javaPrimitiveType!!
        )
        if (mSetStatusBarDarkIcon != null) {
            try {
                mSetStatusBarDarkIcon.invoke(activity, dark)
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            }

        } else {
            if (flag) {
                setStatusBarDarkIcon(activity.window, dark)
            }
        }
    } catch (e: NoSuchMethodException) {
        e.printStackTrace()
    }
}

private fun setStatusBarDarkIcon(window: Window, dark: Boolean) {
    if (Build.VERSION.SDK_INT < 23) {
        changeMeizuFlag(window.attributes, "MEIZU_FLAG_DARK_STATUS_BAR_ICON", dark)
    } else {
        val decorView = window.decorView
        if (decorView != null) {
            setStatusBarDarkIcon(decorView, dark)
            setStatusBarColor(window, 0)
        }
    }
}

private fun changeMeizuFlag(
    winParams: WindowManager.LayoutParams,
    flagName: String,
    on: Boolean
): Boolean {
    try {
        val f = winParams.javaClass.getDeclaredField(flagName)
        f.isAccessible = true
        val bits = f.getInt(winParams)
        val f2 = winParams.javaClass.getDeclaredField("meizuFlags")
        f2.isAccessible = true
        var meizuFlags = f2.getInt(winParams)
        val oldFlags = meizuFlags
        if (on) {
            meizuFlags = meizuFlags or bits
        } else {
            meizuFlags = meizuFlags and bits.inv()
        }
        if (oldFlags != meizuFlags) {
            f2.setInt(winParams, meizuFlags)
            return true
        }
    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
    } catch (e: Throwable) {
        e.printStackTrace()
    }

    return false
}

private fun setStatusBarColor(window: Window, color: Int) {
    val winParams = window.attributes
    try {
        val mStatusBarColorFiled = WindowManager.LayoutParams::class.java.getField("statusBarColor")
        if (mStatusBarColorFiled != null) {
            try {
                val oldColor = mStatusBarColorFiled.getInt(winParams)
                if (oldColor != color) {
                    mStatusBarColorFiled.set(winParams, color)
                    window.attributes = winParams
                }
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

        }
    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    }
}

private var SYSTEM_UI_FLAG_LIGHT_STATUS_BAR = 0

private fun setStatusBarDarkIcon(view: View, dark: Boolean) {
    val oldVis = view.systemUiVisibility
    var newVis = oldVis
    if (dark) {
        newVis = newVis or SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    } else {
        newVis = newVis and SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
    }
    if (newVis != oldVis) {
        view.systemUiVisibility = newVis
    }
}