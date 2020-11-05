package com.martiserramolina.lifeplan.viewmodels.interfaces

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

interface InfoItemViewModel {
    val itemDeleted: MutableLiveData<Boolean>
    fun deleteItem()

    abstract class Object : InfoItemViewModel {
        override val itemDeleted = MutableLiveData<Boolean>().apply { value = false }
        abstract val coroutineScope: CoroutineScope
        override fun deleteItem() {
            coroutineScope.launch {
                deleteItemFromDatabase()
                itemDeleted.value = true
            }
        }

        abstract suspend fun deleteItemFromDatabase()
    }
}