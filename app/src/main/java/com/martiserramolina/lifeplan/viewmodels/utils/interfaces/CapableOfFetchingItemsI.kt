package com.martiserramolina.lifeplan.viewmodels.utils.interfaces

import androidx.lifecycle.MutableLiveData

interface CapableOfFetchingItemsI<T> {
    companion object {
        const val NUM_ITEMS_TO_FETCH = 10
    }
    val itemsFetched: MutableLiveData<MutableList<T>>
    fun fetchItemsIfNotFetched(position: Int, numItems: Int): Boolean
}