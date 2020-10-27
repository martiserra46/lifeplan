package com.martiserramolina.lifeplan.viewmodels.nav.life

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.LifeRepository
import com.martiserramolina.lifeplan.repository.room.AppDb
import com.martiserramolina.lifeplan.repository.room.Life
import com.martiserramolina.lifeplan.viewmodels.abstracts.LifeRepositoryViewModel
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

class LifeViewModel(application: Application) : LifeRepositoryViewModel(application) {

    val life = MutableLiveData<Life>().apply { value = Life() }

    val isLifeLoaded = MutableLiveData<Boolean>().apply { value = false }

    init {
        viewModelScope.launch {
            life.value = repository.getLife()
            isLifeLoaded.value = true
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