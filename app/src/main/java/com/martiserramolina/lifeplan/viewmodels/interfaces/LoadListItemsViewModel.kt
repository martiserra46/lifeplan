package com.martiserramolina.lifeplan.viewmodels.interfaces

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

interface LoadListItemsViewModel<T> {
    companion object { private const val NUM_ITEMS_TO_FETCH = 30 }
    val itemsFetched: MutableLiveData<MutableList<T>>
    fun fetchItemsIfNotFetched(position: Long, numItems: Int = NUM_ITEMS_TO_FETCH): Boolean

    abstract class Object<T>() : LoadListItemsViewModel<T> {

        override val itemsFetched = MutableLiveData<MutableList<T>>().apply { value = mutableListOf() }

        abstract val coroutineScope: CoroutineScope

        private var lastFetchedPosition: Long? = null

        init { fetchItemsIfNotFetched(0) }

        final override fun fetchItemsIfNotFetched(
            position: Long, numItems: Int
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
}