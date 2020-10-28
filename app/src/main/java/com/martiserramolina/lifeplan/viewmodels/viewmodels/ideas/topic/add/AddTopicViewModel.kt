package com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.topic.add

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.topic.TopicViewModel
import kotlinx.coroutines.launch

class AddTopicViewModel(application: Application) : TopicViewModel(application) {
    override var topic = Topic()
    val topicAdded = MutableLiveData<Boolean>().apply { value = false }
    fun addTopic() {
        viewModelScope.launch {
            repository.insertTopic(topic)
            topicAdded.value = true
        }
    }
}