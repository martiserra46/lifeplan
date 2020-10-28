package com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.idea.add

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
}