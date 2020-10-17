package com.martiserramolina.lifeplan.viewmodels.your_life.edit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.repository.Repository
import com.martiserramolina.lifeplan.repository.model.YourLife
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

class EditYourLifeViewModel(application: Application) : AndroidViewModel(application) {

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

    fun insertYourLife(yourLife: YourLife) {
        coroutineMainScope.launch {
            withContext(Dispatchers.IO) { repository.yourLifeRepository.insertYourLife(yourLife) }
        }
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EditYourLifeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EditYourLifeViewModel(application) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}