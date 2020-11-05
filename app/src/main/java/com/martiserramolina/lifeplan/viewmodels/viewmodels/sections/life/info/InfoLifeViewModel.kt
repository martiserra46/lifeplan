package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.life.info

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.AppDb
import com.martiserramolina.lifeplan.repository.room.Life
import com.martiserramolina.lifeplan.viewmodels.interfaces.DeleteItemViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.life.LifeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class InfoLifeViewModel(
    application: Application
) : LifeViewModel(application), DeleteItemViewModel {
    val life = MutableLiveData<Life>().apply { value = Life() }
    val isLifeLoaded = MutableLiveData<Boolean>().apply { value = false }

    private val deleteItemViewModel = object : DeleteItemViewModel.Object() {
        override val coroutineScope get() = viewModelScope
        override suspend fun deleteItemFromDatabase() {
            repository.deleteLife()
            life.value = Life()
        }
    }

    init {
        viewModelScope.launch {
            life.value = repository.getLife()
            isLifeLoaded.value = true
        }
    }

    override val itemDeleted get() = deleteItemViewModel.itemDeleted
    override fun deleteItem() = deleteItemViewModel.deleteItem()
}