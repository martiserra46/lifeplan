package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.idea.info

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.interfaces.DeleteItemViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.idea.IdeaViewModel

class InfoIdeaViewModel(
    idea: Idea,
    topic: Topic,
    application: Application
) : IdeaViewModel(idea, topic, application), DeleteItemViewModel {
    private val infoItemViewModel = object : DeleteItemViewModel.Object() {
        override val coroutineScope = viewModelScope
        override suspend fun deleteItemFromDatabase() {
            repository.deleteIdea(idea)
        }
    }
    override val itemDeleted get() = infoItemViewModel.itemDeleted
    override fun deleteItem() = infoItemViewModel.deleteItem()
}