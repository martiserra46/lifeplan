package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.idea.save

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.idea.IdeaViewModel
import kotlinx.coroutines.launch

abstract class SaveIdeaViewModel(
    idea: Idea,
    topic: Topic,
    application: Application
) : IdeaViewModel(idea, topic, application) {
    val ideaSaved = MutableLiveData<Boolean>().apply { value = false }
    fun saveIdea() {
        viewModelScope.launch {
            saveIdeaToDatabase()
            ideaSaved.value = true
        }
    }

    abstract suspend fun saveIdeaToDatabase()
}