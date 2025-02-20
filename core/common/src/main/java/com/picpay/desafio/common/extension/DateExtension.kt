package com.picpay.desafio.common.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toText(pattern: String): String {
    return SimpleDateFormat(pattern, Locale.getDefault()).format(this)
}