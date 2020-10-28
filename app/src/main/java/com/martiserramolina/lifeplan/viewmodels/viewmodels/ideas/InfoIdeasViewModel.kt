package com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.viewmodels.IdeasRepositoryViewModel
import java.lang.IllegalArgumentException

class InfoIdeasViewModel(application: Application) : IdeasViewModel(application) {

    companion object { private const val NUM_TOPICS_TO_FETCH = 20 }

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
            if (modelClass.isAssignableFrom(InfoIdeasViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return InfoIdeasViewModel(application) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}