package com.martiserramolina.lifeplan.viewmodels.nav.ideas.topic.save.add

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.IdeasRepository
import com.martiserramolina.lifeplan.repository.room.AppDb
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.abstracts.IdeasRepositoryViewModel
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

class AddTopicViewModel(application: Application) : IdeasRepositoryViewModel(application) {

    val topic = Topic()

    val topicAdded = MutableLiveData<Boolean>().apply { value = false }

    fun addTopic() {
        viewModelScope.launch {
            repository.insertTopic(topic)
            topicAdded.value = true
        }
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddTopicViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AddTopicViewModel(application) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}