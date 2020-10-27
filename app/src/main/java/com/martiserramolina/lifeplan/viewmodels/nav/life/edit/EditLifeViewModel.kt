package com.martiserramolina.lifeplan.viewmodels.nav.life.edit

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.LifeRepository
import com.martiserramolina.lifeplan.repository.room.AppDb
import com.martiserramolina.lifeplan.repository.room.Life
import com.martiserramolina.lifeplan.viewmodels.abstracts.LifeRepositoryViewModel
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

class EditLifeViewModel(
    val life: Life,
    application: Application
) : LifeRepositoryViewModel(application) {

    val lifeEdited = MutableLiveData<Boolean>().apply { value = false }

    fun editLife() {
        viewModelScope.launch {
            repository.insertLife(life)
            lifeEdited.value = true
        }
    }

    class Factory(private val life: Life, private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EditLifeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EditLifeViewModel(life, application) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}