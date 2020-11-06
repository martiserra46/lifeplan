package com.martiserramolina.lifeplan.viewmodels.interfaces

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

interface LoadListItemsViewModel<T> {

    companion object {
        private const val NUM_ITEMS_TO_FETCH = 30
    }

    val itemsFetched: MutableLiveData<MutableList<T>>
    var lastFetchedPosition: Long?
    var allItemsFetched: Boolean

    fun fetchItemsIfNotFetched(position: Long, numItems: Int = NUM_ITEMS_TO_FETCH): Boolean


    abstract class Object<T> : LoadListItemsViewModel<T> {

        override val itemsFetched = MutableLiveData<MutableList<T>>().apply { value = mutableListOf() }

        override var allItemsFetched: Boolean = false

        override var lastFetchedPosition: Long? = null

        abstract val coroutineScope: CoroutineScope

        init {
            fetchItemsIfNotFetched(0)
        }

        final override fun fetchItemsIfNotFetched(
            position: Long, numItems: Int
        ): Boolean {
            synchronized(this) {
                if (allItemsFetched || position == lastFetchedPosition) return false
                lastFetchedPosition = position
                coroutineScope.launch {
                    val items = getItemsFromDatabase(position, numItems)
                    if (items.size < numItems) allItemsFetched = true
                    itemsFetched.value = itemsFetched.value?.apply { addAll(items) }
                }
                return true
            }
        }

        abstract suspend fun getItemsFromDatabase(position: Long, numItems: Int): List<T>
    }
}