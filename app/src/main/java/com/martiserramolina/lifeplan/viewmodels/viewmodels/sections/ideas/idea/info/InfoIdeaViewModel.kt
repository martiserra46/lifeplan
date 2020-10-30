package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.idea.info

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.idea.IdeaViewModel
import kotlinx.coroutines.launch

class InfoIdeaViewModel(
    idea: Idea,
    topic: Topic,
    application: Application
) : IdeaViewModel(idea, topic, application) {
    val ideaDeleted = MutableLiveData<Boolean>().apply { value = false }
    fun deleteIdea() {
        viewModelScope.launch {
            repository.deleteIdea(idea)
            ideaDeleted.value = true
        }
    }
}