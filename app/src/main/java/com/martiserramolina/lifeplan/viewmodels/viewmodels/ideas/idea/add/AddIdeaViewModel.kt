package com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.idea.add

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.idea.IdeaViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.idea.info.InfoIdeaViewModel
import kotlinx.coroutines.launch

class AddIdeaViewModel(
    topic: Topic,
    application: Application
) : IdeaViewModel(Idea(), topic, application) {
    val ideaAdded = MutableLiveData<Boolean>().apply { value = false }
    fun addIdea() {
        viewModelScope.launch {
            repository.insertIdea(idea)
            ideaAdded.value = true
        }
    }
}