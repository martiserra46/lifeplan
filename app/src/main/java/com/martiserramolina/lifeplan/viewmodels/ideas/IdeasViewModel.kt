package com.martiserramolina.lifeplan.viewmodels.ideas

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.IdeasRepository
import com.martiserramolina.lifeplan.repository.room.AppDb
import com.martiserramolina.lifeplan.repository.room.Topic
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

class IdeasViewModel(application: Application) : AndroidViewModel(application) {

    private val repository by lazy {
        IdeasRepository(AppDb.getInstance(application.applicationContext).daoIdeas())
    }

    val topics = MutableLiveData<List<Topic>>()

    init {
        viewModelScope.launch {
            topics.value = repository.getTopics()
        }
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(IdeasViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return IdeasViewModel(application) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}