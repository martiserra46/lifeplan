package com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.idea.save

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.enums.SaveOperation
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.idea.IdeaViewModel
import kotlinx.coroutines.launch

abstract class SaveIdeaViewModel(
    idea: Idea,
    topic: Topic,
    application: Application,
    private val saveOperation: SaveOperation
) : IdeaViewModel(idea, topic, application) {
    val ideaSaved = MutableLiveData<Boolean>().apply { value = false }
    fun saveIdea() {
        viewModelScope.launch {
            when (saveOperation) {
                SaveOperation.ADD -> repository.insertIdea(idea)
                SaveOperation.EDIT -> repository.updateIdea(idea)
            }
            ideaSaved.value = true
        }
    }
}