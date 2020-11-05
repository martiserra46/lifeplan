package com.martiserramolina.lifeplan.utils.extensions

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat

fun Activity.hideKeyboard() {
    ContextCompat.getSystemService(
        this, InputMethodManager::class.java
    )?.hideSoftInputFromWindow((currentFocus ?: View(this)).windowToken, 0)
}