package com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.topic.save

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.enums.SaveOperation
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.topic.TopicViewModel
import kotlinx.coroutines.launch

abstract class SaveTopicViewModel(
    topic: Topic,
    application: Application,
    private val saveOperation: SaveOperation
) : TopicViewModel(topic, application) {
    val topicSaved = MutableLiveData<Boolean>().apply { value = false }
    fun saveTopic() {
        viewModelScope.launch {
            when (saveOperation) {
                SaveOperation.ADD -> repository.insertTopic(topic)
                SaveOperation.EDIT -> repository.updateTopic(topic)
            }
            topicSaved.value = true
        }
    }
}