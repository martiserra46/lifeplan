package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.info

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.interfaces.LoadListItemsViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.IdeasViewModel

class InfoIdeasViewModel(
    application: Application
) : IdeasViewModel(application), LoadListItemsViewModel<Topic> {
    private val loadListItemsViewModel = object : LoadListItemsViewModel.Object<Topic>() {
        override val coroutineScope get() = viewModelScope
        override suspend fun getItemsFromDatabase(position: Long, numItems: Int): List<Topic> {
            return repository.getTopics(position, numItems)
        }
    }

    override val itemsFetched get() = loadListItemsViewModel.itemsFetched
    override fun fetchItemsIfNotFetched(position: Long, numItems: Int): Boolean {
        return loadListItemsViewModel.fetchItemsIfNotFetched(position, numItems)
    }
}