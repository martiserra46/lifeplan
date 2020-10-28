package com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.topic.edit

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.topic.TopicViewModel
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class EditTopicViewModel(
    topic: Topic,
    application: Application
) : TopicViewModel(topic, application) {
    val topicEdited = MutableLiveData<Boolean>().apply { value = false }
    fun editTopic() {
        viewModelScope.launch {
            repository.updateTopic(topic)
            topicEdited.value = true
        }
    }
}