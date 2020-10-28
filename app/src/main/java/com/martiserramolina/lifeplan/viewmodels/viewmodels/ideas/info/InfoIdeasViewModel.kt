package com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.info

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.IdeasViewModel
import kotlinx.coroutines.launch

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
}