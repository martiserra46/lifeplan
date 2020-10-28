package com.martiserramolina.lifeplan.viewmodels.viewmodels.life.info

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Life
import com.martiserramolina.lifeplan.viewmodels.viewmodels.LifeRepositoryViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.life.LifeViewModel
import java.lang.IllegalArgumentException

class InfoLifeViewModel(application: Application) : LifeViewModel(application) {

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