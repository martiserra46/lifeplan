package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.topic.info

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.interfaces.LoadListItemsViewModel
import com.martiserramolina.lifeplan.viewmodels.interfaces.DeleteItemViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.topic.TopicViewModel

class InfoTopicViewModel(
    topic: Topic,
    application: Application
) : TopicViewModel(topic, application),
    LoadListItemsViewModel<Idea>, DeleteItemViewModel {
    private val loadListItemsViewModel = object : LoadListItemsViewModel.Object<Idea>() {
        override val coroutineScope get() = viewModelScope
        override suspend fun getItemsFromDatabase(position: Long, numItems: Int): List<Idea> {
            return repository.getIdeas(topic.topicId, position, numItems)
        }
    }

    private val deleteItemViewModel = object : DeleteItemViewModel.Object() {
        override val coroutineScope = viewModelScope
        override suspend fun deleteItemFromDatabase() {
            repository.deleteTopic(topic)
        }
    }

    override val itemsFetched get() = loadListItemsViewModel.itemsFetched
    override fun fetchItemsIfNotFetched(position: Long, numItems: Int) =
        loadListItemsViewModel.fetchItemsIfNotFetched(position, numItems)

    override val itemDeleted get() = deleteItemViewModel.itemDeleted
    override fun deleteItem() = deleteItemViewModel.deleteItem()
}