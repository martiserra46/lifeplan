package com.martiserramolina.lifeplan.viewmodels.utils.classes

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class CapableOfFetchingItems<T>() {
    companion object {
        private const val NUM_ITEMS_TO_FETCH = 20
    }

    val itemsFetched = MutableLiveData<MutableList<T>>().apply { value = mutableListOf() }

    protected abstract val coroutineScope: CoroutineScope
    private var lastFetchedPosition: Int? = null

    fun fetchItemsIfNotFetched(
        position: Int,
        numItems: Int = NUM_ITEMS_TO_FETCH
    ): Boolean {
        synchronized(this) {
            if (position == lastFetchedPosition) return false
            lastFetchedPosition = position
            coroutineScope.launch {
                itemsFetched.value = itemsFetched.value?.apply { addAll(getItemsFromDatabase()) }
            }
            return true
        }
    }

    abstract suspend fun getItemsFromDatabase(): List<T>
}