package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.life.info

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Life
import com.martiserramolina.lifeplan.viewmodels.interfaces.DeleteItemViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.life.LifeViewModel
import kotlinx.coroutines.launch

class InfoLifeViewModel(
    application: Application
) : LifeViewModel(application), DeleteItemViewModel {
    val life = MutableLiveData<Life?>().apply { value = null }

    private val deleteItemViewModel = object : DeleteItemViewModel.Object() {
        override val coroutineScope get() = viewModelScope
        override suspend fun deleteItemFromDatabase() {
            repository.deleteLife()
            life.value = null
        }
    }

    init {
        viewModelScope.launch {
            life.value = repository.getLife()
        }
    }

    override val itemDeleted get() = deleteItemViewModel.itemDeleted
    override fun deleteItem() = deleteItemViewModel.deleteItem()
}