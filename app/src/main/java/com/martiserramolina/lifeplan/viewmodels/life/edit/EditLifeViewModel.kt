package com.martiserramolina.lifeplan.viewmodels.life.edit

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.LifeRepository
import com.martiserramolina.lifeplan.repository.room.AppDb
import com.martiserramolina.lifeplan.repository.room.Life
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

class EditLifeViewModel(
    val life: Life?,
    application: Application
) : AndroidViewModel(application) {

    private val repository by lazy {
        LifeRepository(AppDb.getInstance(application.applicationContext).daoLife())
    }

    fun insertLife(life: Life) {
        viewModelScope.launch {
            repository.insertLife(life)
        }
    }

    class Factory(private val life: Life?, private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EditLifeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EditLifeViewModel(life, application) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}