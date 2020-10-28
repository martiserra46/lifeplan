package com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.idea

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.viewmodels.IdeasRepositoryViewModel
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
}