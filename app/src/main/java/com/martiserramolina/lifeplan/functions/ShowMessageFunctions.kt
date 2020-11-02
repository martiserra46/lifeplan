package com.martiserramolina.lifeplan.functions

import android.app.Activity
import android.graphics.Typeface
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.extensions.hideKeyboard

fun showMessageWithDelay(
    view: View,
    messageId: Int,
    duration: Int = Snackbar.LENGTH_SHORT,
    delay: Long = view.context.resources
        .getInteger(R.integer.animation_start_offset).toLong()
) {
    (view.context as Activity).hideKeyboard()
    Handler(Looper.getMainLooper()).postDelayed({
        showMessage(view, messageId, duration)
    }, delay)
}

fun showMessage(view: View, messageId: Int, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(view, messageId, duration).apply {
        setBackgroundTint(ContextCompat.getColor(context, R.color.colorSnackBarBackground))
        setTextColor(ContextCompat.getColor(context, R.color.colorSnackBarText))
        setActionTextColor(ContextCompat.getColor(context, R.color.colorSnackBarActionText))
        setAction(R.string.close) { dismiss() }
    }.apply {
        getView().findViewById<TextView>(
            com.google.android.material.R.id.snackbar_text
        ).typeface = Typeface.createFromAsset(context.assets, "fonts/open_sans.ttf")
        getView().findViewById<TextView>(
            com.google.android.material.R.id.snackbar_action
        ).typeface = Typeface.createFromAsset(context.assets, "fonts/open_sans.ttf")
    }.show()
}