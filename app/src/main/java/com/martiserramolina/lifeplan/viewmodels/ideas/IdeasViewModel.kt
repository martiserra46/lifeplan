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

    val topics = MutableLiveData<MutableList<Topic>>()

    private var nextPositionToFetchTopics: Int = 0
    private val numTopicsToFetch: Int = 10

    init {
        viewModelScope.launch {
            topics.value = mutableListOf(
                *repository.getTopics(nextPositionToFetchTopics++, numTopicsToFetch).toTypedArray()
            )
        }
    }

    fun fetchTopicsFromNextPosition() {
        nextPositionToFetchTopics += numTopicsToFetch
        viewModelScope.launch {
            topics.value = topics.value?.apply {
                addAll(repository.getTopics(nextPositionToFetchTopics++, numTopicsToFetch))
            }
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