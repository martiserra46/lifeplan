package com.martiserramolina.lifeplan.viewmodels.ideas

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.IdeasRepository
import com.martiserramolina.lifeplan.repository.room.AppDb
import com.martiserramolina.lifeplan.repository.room.Topic
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

class IdeasViewModel(application: Application) : AndroidViewModel(application) {

    companion object { private const val NUM_TOPICS_TO_FETCH = 20 }

    private val repository by lazy {
        IdeasRepository(AppDb.getInstance(application.applicationContext).daoIdeas())
    }

    val topics = MutableLiveData<MutableList<Topic>>().apply { value = mutableListOf() }

    private var lastTopicPositionUsedToFetch: Int? = null

    init { fetchTopicsFromPositionIfNotFetched(0) }

    fun fetchTopicsFromPositionIfNotFetched(position: Int): Boolean {
        if (position == lastTopicPositionUsedToFetch) return false
        lastTopicPositionUsedToFetch = position
        viewModelScope.launch {
            topics.value = topics.value?.apply {
                addAll(repository.getTopics(position, NUM_TOPICS_TO_FETCH))
            }
        }
        return true
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