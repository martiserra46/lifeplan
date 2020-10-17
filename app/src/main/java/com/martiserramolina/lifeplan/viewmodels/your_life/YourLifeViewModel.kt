package com.martiserramolina.lifeplan.viewmodels.your_life

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.Repository
import com.martiserramolina.lifeplan.repository.model.YourLife
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

class YourLifeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository(application.applicationContext)

    private val coroutineJob = Job()
    private val coroutineMainScope = CoroutineScope(Dispatchers.Main + coroutineJob)

    val yourLife = MutableLiveData<YourLife?>().apply { value = null }

    init {
        coroutineMainScope.launch {
            yourLife.value = withContext(Dispatchers.IO) {
                repository.yourLifeRepository.getYourLife()
            }
        }
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(YourLifeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return YourLifeViewModel(application) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}