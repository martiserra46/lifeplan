package com.martiserramolina.lifeplan.viewmodels.interfaces

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

interface LoadItemsViewModel<T> {
    val items: LiveData<PagedList<T>>
}