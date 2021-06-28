package com.example.moviedb.utils

import java.security.MessageDigest
import java.text.NumberFormat
import java.util.*

object Converter {

    fun idr(number: String): String{
        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number.toDouble()).toString()
    }
    fun hash(value:String): String {
        val bytes = value.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }
}