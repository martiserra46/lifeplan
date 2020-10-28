package com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.idea.save.add

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.viewmodels.IdeasRepositoryViewModel
import java.lang.IllegalArgumentException

class AddIdeaViewModel(
    val topic: Topic, application: Application
) : IdeasRepositoryViewModel(application) {

    val idea = Idea()

    val ideaAdded = MutableLiveData<Boolean>().apply { value = false }

    fun addIdea() {
        viewModelScope.launch {
            repository.insertIdea(idea)
            ideaAdded.value = true
        }
    }

    class Factory(
        private val topic: Topic, private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddIdeaViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AddIdeaViewModel(topic, application) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}