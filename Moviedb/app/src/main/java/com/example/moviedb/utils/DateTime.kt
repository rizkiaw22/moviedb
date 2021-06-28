package com.example.moviedb.utils

import java.text.SimpleDateFormat
import java.util.*

object DateTime {
    fun now():String{
        var dateStr=""
        val sdf = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss ")
        sdf.timeZone= TimeZone.getTimeZone("GMT")
        dateStr = sdf.format(Date())
        return dateStr
    }
}