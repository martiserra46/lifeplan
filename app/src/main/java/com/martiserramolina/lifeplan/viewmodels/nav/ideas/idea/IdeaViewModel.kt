package com.martiserramolina.lifeplan.viewmodels.nav.ideas.idea

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.IdeasRepository
import com.martiserramolina.lifeplan.repository.room.AppDb
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.abstracts.IdeasRepositoryViewModel
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class IdeaViewModel(
    val idea: Idea, val topic: Topic, application: Application
) : IdeasRepositoryViewModel(application) {

    val ideaDeleted = MutableLiveData<Boolean>().apply { value = false }

    fun deleteIdea() {
        viewModelScope.launch {
            repository.deleteIdea(idea)
            ideaDeleted.value = true
        }
    }

    class Factory(
        private val topic: Topic, private val idea: Idea, private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(IdeaViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return IdeaViewModel(idea, topic, application) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}