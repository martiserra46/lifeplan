package com.martiserramolina.lifeplan.viewmodels.capable_of_fetching_items

import androidx.lifecycle.MutableLiveData

interface CapableOfFetchingItemsI<T> {
    companion object { private const val NUM_ITEMS_TO_FETCH = 30 }
    val itemsFetched: MutableLiveData<MutableList<T>>
    fun fetchItemsIfNotFetched(position: Long, numItems: Int = NUM_ITEMS_TO_FETCH): Boolean
}