package com.martiserramolina.lifeplan.viewmodels.interfaces

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

interface SaveItemViewModel {
    val itemSaved: MutableLiveData<Boolean>
    fun saveItem()

    abstract class Object : SaveItemViewModel {
        override val itemSaved = MutableLiveData<Boolean>().apply { value = false }
        abstract val coroutineScope: CoroutineScope
        override fun saveItem() {
            coroutineScope.launch {
                saveItemToDatabase()
                itemSaved.value = true
            }
        }
        abstract suspend fun saveItemToDatabase()
    }
}