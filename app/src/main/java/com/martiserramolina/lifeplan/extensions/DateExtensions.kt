package com.martiserramolina.lifeplan.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatted(): String {
    return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(this)
}