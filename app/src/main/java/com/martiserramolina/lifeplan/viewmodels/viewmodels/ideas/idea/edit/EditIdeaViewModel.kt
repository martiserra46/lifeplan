package com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.idea.edit

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.viewmodels.IdeasRepositoryViewModel
import java.lang.IllegalArgumentException

class EditIdeaViewModel(
    val idea: Idea, val topic: Topic, application: Application
) : IdeasRepositoryViewModel(application) {

    val ideaEdited = MutableLiveData<Boolean>().apply { value = false }

    fun editIdea() {
        viewModelScope.launch {
            repository.updateIdea(idea)
            ideaEdited.value = true
        }
    }
}