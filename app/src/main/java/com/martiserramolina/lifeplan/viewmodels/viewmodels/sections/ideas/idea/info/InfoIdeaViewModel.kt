package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.idea.info

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.interfaces.InfoItemViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.idea.IdeaViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class InfoIdeaViewModel(
    idea: Idea,
    topic: Topic,
    application: Application
) : IdeaViewModel(idea, topic, application), InfoItemViewModel {
    private val infoItemViewModel = object : InfoItemViewModel.Object() {
        override val coroutineScope = viewModelScope
        override suspend fun deleteItemFromDatabase() {
            repository.deleteIdea(idea)
        }
    }
    override val itemDeleted get() = infoItemViewModel.itemDeleted
    override fun deleteItem() = infoItemViewModel.deleteItem()
}