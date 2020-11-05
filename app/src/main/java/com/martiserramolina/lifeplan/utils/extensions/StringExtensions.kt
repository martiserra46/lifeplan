package com.martiserramolina.lifeplan.utils.extensions

fun String.formattedWithMaxLength(maxLength: Int): String {
    val positionLineBreak = indexOf("\n")
    return if (positionLineBreak != -1 && positionLineBreak <= maxLength)
        substring(0, positionLineBreak).trim() + "..."
    else if (length <= maxLength) this
    else substring(0, maxLength - 2).trim() + "..."
}