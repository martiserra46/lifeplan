package com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.topic.edit

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.viewmodels.IdeasRepositoryViewModel
import java.lang.IllegalArgumentException

class EditTopicViewModel(
    val topic: Topic, application: Application
) : IdeasRepositoryViewModel(application) {

    val topicEdited = MutableLiveData<Boolean>().apply { value = false }

    fun editTopic() {
        viewModelScope.launch {
            repository.updateTopic(topic)
            topicEdited.value = true
        }
    }

    class Factory(
        private val topic: Topic, private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EditTopicViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EditTopicViewModel(topic, application) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}