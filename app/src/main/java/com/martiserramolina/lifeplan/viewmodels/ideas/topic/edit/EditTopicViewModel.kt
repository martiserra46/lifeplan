package com.martiserramolina.lifeplan.viewmodels.ideas.topic.edit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.martiserramolina.lifeplan.repository.IdeasRepository
import com.martiserramolina.lifeplan.repository.room.AppDb
import com.martiserramolina.lifeplan.repository.room.Topic
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class EditTopicViewModel(var topic: Topic, application: Application) : AndroidViewModel(application) {

    private val repository by lazy {
        IdeasRepository(AppDb.getInstance(application.applicationContext).daoIdeas())
    }

    fun updateTopic(topic: Topic) {
        viewModelScope.launch {
            repository.updateTopic(topic)
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