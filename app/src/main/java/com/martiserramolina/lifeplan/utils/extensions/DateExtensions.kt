package com.martiserramolina.lifeplan.utils.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatted(): String {
    return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(this)
}