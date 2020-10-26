package com.martiserramolina.lifeplan.viewmodels.life

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.LifeRepository
import com.martiserramolina.lifeplan.repository.room.AppDb
import com.martiserramolina.lifeplan.repository.room.Life
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

class LifeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository by lazy {
        LifeRepository(AppDb.getInstance(application.applicationContext).daoLife())
    }

    val life = MutableLiveData<Life>().apply { value = Life() }

    init {
        viewModelScope.launch {
            life.value = repository.getLife()
        }
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LifeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return LifeViewModel(application) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}