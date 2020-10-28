package com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.topic.info

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.topic.TopicViewModel
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class InfoTopicViewModel(
    override val topic: Topic,
    application: Application
) : TopicViewModel(application) {

    companion object {
        private const val NUM_IDEAS_TO_FETCH = 20
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
}