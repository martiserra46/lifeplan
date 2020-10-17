package com.martiserramolina.lifeplan.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(format: String): String {
    return SimpleDateFormat(format, Locale.getDefault()).format(this)
}