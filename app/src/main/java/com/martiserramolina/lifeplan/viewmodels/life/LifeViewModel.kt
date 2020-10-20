package com.martiserramolina.lifeplan.viewmodels.life

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.Repository
import com.martiserramolina.lifeplan.repository.model.Life
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

class LifeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository(application.applicationContext)

    private val coroutineJob = Job()
    private val coroutineMainScope = CoroutineScope(Dispatchers.Main + coroutineJob)

    val life = MutableLiveData<Life?>().apply { value = null }

    init {
        coroutineMainScope.launch {
            life.value = withContext(Dispatchers.IO) {
                repository.lifeRepository.getLife()
            }
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