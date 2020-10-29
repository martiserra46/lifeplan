package com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.topic.save

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.topic.TopicViewModel
import kotlinx.coroutines.launch

abstract class SaveTopicViewModel(
    topic: Topic,
    application: Application
) : TopicViewModel(topic, application) {
    val topicSaved = MutableLiveData<Boolean>().apply { value = false }
    fun saveTopic() {
        viewModelScope.launch {
            saveTopicToDatabase()
            topicSaved.value = true
        }
    }

    abstract suspend fun saveTopicToDatabase()
}