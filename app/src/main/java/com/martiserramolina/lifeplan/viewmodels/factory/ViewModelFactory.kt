package com.martiserramolina.lifeplan.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import kotlin.reflect.KProperty

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

    class Delegate<T : ViewModel>(
        viewModelStoreOwner: ViewModelStoreOwner,
        className: Class<T>,
        buildInstance: () -> T
    ) {
        private val viewModel by lazy {
            ViewModelProvider(
                viewModelStoreOwner,
                ViewModelFactory(className) { buildInstance() }
            ).get(className)
        }
        operator fun getValue(thisRef: Any?, property: KProperty<*>): T = viewModel
    }
}