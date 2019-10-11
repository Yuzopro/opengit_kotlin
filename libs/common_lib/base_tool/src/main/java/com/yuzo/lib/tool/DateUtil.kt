package com.yuzo.lib.tool

import java.text.SimpleDateFormat
import java.util.*

/**
 * Author: yuzo
 * Date: 2019-10-11
 */
fun transTimeStamp(time: String?): Long =
    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        .let {
            it.timeZone = TimeZone.getTimeZone("GMT+1")
            it.parse(time).time
        }

fun getMultiTime(timestamp: Long?): String {
    var result = ""
    val weekNames = arrayOf("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")
    val hourTimeFormat = "HH:mm"
    val monthTimeFormat = "M月d日 HH:mm"
    val yearTimeFormat = "yyyy年M月d日 HH:mm"
    try {
        val todayCalendar = Calendar.getInstance()
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp!!

        if (todayCalendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {//当年
            if (todayCalendar.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)) {//当月
                val temp =
                    todayCalendar.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH)
                when (temp) {
                    0//今天
                    -> result = "今天 " + getTime(timestamp, hourTimeFormat)
                    1//昨天
                    -> result = "昨天 " + getTime(timestamp, hourTimeFormat)
                    2, 3, 4, 5, 6 -> {
                        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
                        result = weekNames[dayOfWeek - 1] + " " + getTime(timestamp, hourTimeFormat)
                    }
                    else -> result = getTime(timestamp, monthTimeFormat)
                }
            } else {
                result = getTime(timestamp, monthTimeFormat)
            }
        } else {
            result = getTime(timestamp, yearTimeFormat)
        }
        return result
    } catch (e: Exception) {
        return ""
    }

}

private fun getTime(time: Long, pattern: String): String {
    val date = Date(time)
    return dateFormat(date, pattern)
}

private fun dateFormat(date: Date, pattern: String): String {
    val format = SimpleDateFormat(pattern, Locale.getDefault())
    return format.format(date)
}