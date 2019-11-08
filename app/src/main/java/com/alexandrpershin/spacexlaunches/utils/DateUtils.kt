package com.alexandrpershin.spacexlaunches.utils

import android.content.Context

object DateUtils {


    fun unixToStringDate(context: Context, unix: Long): String {
        return android.text.format.DateUtils.formatDateTime(
            context,
            unix * 1000,
            android.text.format.DateUtils.FORMAT_SHOW_DATE
        )
    }
}