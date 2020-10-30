package com.martiserramolina.lifeplan.viewmodels.utils.classes

import androidx.lifecycle.MutableLiveData
import com.martiserramolina.lifeplan.viewmodels.utils.interfaces.CapableOfFetchingItemsI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class CapableOfFetchingItems<T>() : CapableOfFetchingItemsI<T> {

    override val itemsFetched = MutableLiveData<MutableList<T>>().apply { value = mutableListOf() }

    protected abstract val coroutineScope: CoroutineScope

    private var lastFetchedPosition: Int? = null

    override fun fetchItemsIfNotFetched(
        position: Int,
        numItems: Int
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