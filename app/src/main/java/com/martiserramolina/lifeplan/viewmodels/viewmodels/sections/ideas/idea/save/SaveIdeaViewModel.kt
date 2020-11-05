package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.idea.save

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.interfaces.SaveItemViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.idea.IdeaViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class SaveIdeaViewModel(
    idea: Idea,
    topic: Topic,
    application: Application
) : IdeaViewModel(idea, topic, application), SaveItemViewModel {
    private val saveItemViewModel = object : SaveItemViewModel.Object() {
        override val coroutineScope = viewModelScope
        override suspend fun saveItemToDatabase() {
            this@SaveIdeaViewModel.saveItemToDatabase()
        }
    }
    override val itemSaved get() = saveItemViewModel.itemSaved
    override fun saveItem() = saveItemViewModel.saveItem()

    abstract suspend fun saveItemToDatabase()
}