package com.martiserramolina.lifeplan.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory<T : ViewModel>(
    private val className: Class<T>,
    private val buildInstance: () -> T
) : ViewModelProvider.Factory {
    override fun <U : ViewModel?> create(modelClass: Class<U>): U {
        if (modelClass.isAssignableFrom(className)) {
            @Suppress("UNCHECKED_CAST")
            return buildInstance() as U
        }
        throw IllegalArgumentException("Invalid ViewModel")
    }
}