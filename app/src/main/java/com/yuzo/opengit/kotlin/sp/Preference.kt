package com.yuzo.opengit.kotlin.sp

import android.content.Context
import android.content.SharedPreferences
import com.yuzo.opengit.kotlin.BaseApplication
import kotlin.reflect.KProperty

/**
 * Author: yuzo
 * Date: 2019-09-29
 */
private const val DEFAULT_SP_TAG = "Prefs_Open_Git"

class Preference<T>(val name: String, val default: T) {
    val prefs: SharedPreferences by lazy {
        BaseApplication.INSTANCE.applicationContext.getSharedPreferences(
            DEFAULT_SP_TAG,
            Context.MODE_PRIVATE
        )
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T =
        getSharedPreferences(name, default)

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) =
        putSharedPreferences(name, value)


    private fun putSharedPreferences(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Int -> putInt(name, value)
            is Float -> putFloat(name, value)
            is Long -> putLong(name, value)
            is Boolean -> putBoolean(name, value)
            is String -> putString(name, value)
            else -> throw IllegalArgumentException("SharedPreference can't be save this type")
        }.apply()
    }

    private fun getSharedPreferences(name: String, default: T): T = with(prefs) {
        val res: Any = when (default) {
            is Int -> getInt(name, default)
            is Float -> getFloat(name, default)
            is Long -> getLong(name, default)
            is Boolean -> getBoolean(name, default)
            is String -> getString(name, default)
            else -> throw IllegalArgumentException("SharedPreference can't be get this type")
        }
        return res as T
    }
}