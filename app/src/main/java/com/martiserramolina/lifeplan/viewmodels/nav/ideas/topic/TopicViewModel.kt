package com.martiserramolina.lifeplan.viewmodels.nav.ideas.topic

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.IdeasRepository
import com.martiserramolina.lifeplan.repository.room.AppDb
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.repository.room.Topic
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class TopicViewModel(val topic: Topic, application: Application) : AndroidViewModel(application) {

    companion object {
        private const val NUM_IDEAS_TO_FETCH = 20
    }

    private val repository by lazy {
        IdeasRepository(AppDb.getInstance(application.applicationContext).daoIdeas())
    }

    val topicDeleted = MutableLiveData<Boolean>().apply { value = false }

    val ideas = MutableLiveData<MutableList<Idea>>().apply { value = mutableListOf() }

    private var lastIdeaPositionUsedToFetch: Int? = null

    init { fetchIdeasFromPositionIfNotFetched(0) }

    fun fetchIdeasFromPositionIfNotFetched(position: Int): Boolean {
        if (position == lastIdeaPositionUsedToFetch) return false
        lastIdeaPositionUsedToFetch = position
        viewModelScope.launch {
            ideas.value = ideas.value?.apply {
                addAll(repository.getIdeas(topic.topicId, position, NUM_IDEAS_TO_FETCH))
            }
        }
        return true
    }

    fun deleteTopic() {
        viewModelScope.launch {
            repository.deleteTopic(topic)
            topicDeleted.value = true
        }
    }

    class Factory(
        private val topic: Topic, private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TopicViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return TopicViewModel(topic, application) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}