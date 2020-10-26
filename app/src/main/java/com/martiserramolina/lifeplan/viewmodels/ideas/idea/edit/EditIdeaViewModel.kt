package com.martiserramolina.lifeplan.viewmodels.ideas.idea.edit

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.IdeasRepository
import com.martiserramolina.lifeplan.repository.room.AppDb
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.repository.room.Topic
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class EditIdeaViewModel(
    var idea: Idea, val topic: Topic, application: Application
) : AndroidViewModel(application) {

    private val repository by lazy {
        IdeasRepository(AppDb.getInstance(application.applicationContext).daoIdeas())
    }

    val ideaUpdated = MutableLiveData<Boolean>().apply { value = false }

    fun updateIdea(idea: Idea) {
        viewModelScope.launch {
            repository.updateIdea(idea)
            ideaUpdated.value = true
        }
    }

    class Factory(
        private val topic: Topic, private val idea: Idea, private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EditIdeaViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EditIdeaViewModel(idea, topic, application) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}