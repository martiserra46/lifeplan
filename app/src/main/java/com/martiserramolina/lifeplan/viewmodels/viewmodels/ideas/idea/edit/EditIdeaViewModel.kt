package com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.idea.edit

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.idea.IdeaViewModel
import kotlinx.coroutines.launch

class EditIdeaViewModel(
    override var idea: Idea,
    override val topic: Topic,
    application: Application
) : IdeaViewModel(application) {
    val ideaEdited = MutableLiveData<Boolean>().apply { value = false }
    fun editIdea() {
        viewModelScope.launch {
            repository.updateIdea(idea)
            ideaEdited.value = true
        }
    }
}