package com.martiserramolina.lifeplan.viewmodels.utils.classes

import androidx.lifecycle.MutableLiveData
import com.martiserramolina.lifeplan.viewmodels.utils.interfaces.CapableOfFetchingItemsI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class CapableOfFetchingItems<T>(
    private val coroutineScope: CoroutineScope
) : CapableOfFetchingItemsI<T> {

    override val itemsFetched = MutableLiveData<MutableList<T>>().apply { value = mutableListOf() }

    private var lastFetchedPosition: Long? = null

    init { fetchItemsIfNotFetched(0) }

    final override fun fetchItemsIfNotFetched(
        position: Long,
        numItems: Int
    ): Boolean {
        synchronized(this) {
            if (position == lastFetchedPosition) return false
            lastFetchedPosition = position
            coroutineScope.launch {
                itemsFetched.value = itemsFetched.value?.apply {
                    addAll(getItemsFromDatabase(position, numItems))
                }
            }
            return true
        }
    }

    abstract suspend fun getItemsFromDatabase(position: Long, numItems: Int): List<T>
}