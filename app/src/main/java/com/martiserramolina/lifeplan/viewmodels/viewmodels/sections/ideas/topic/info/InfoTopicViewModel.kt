package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.topic.info

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.capable_of_fetching_items.CapableOfFetchingItems
import com.martiserramolina.lifeplan.viewmodels.capable_of_fetching_items.CapableOfFetchingItemsI
import com.martiserramolina.lifeplan.viewmodels.interfaces.DeleteItemViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.topic.TopicViewModel

class InfoTopicViewModel(
    topic: Topic,
    application: Application
) : TopicViewModel(topic, application),
    CapableOfFetchingItemsI<Idea>, DeleteItemViewModel {
    private val infoItemViewModel = object : DeleteItemViewModel.Object() {
        override val coroutineScope = viewModelScope
        override suspend fun deleteItemFromDatabase() {
            repository.deleteTopic(topic)
        }
    }
    override val itemDeleted get() = infoItemViewModel.itemDeleted
    override fun deleteItem() = infoItemViewModel.deleteItem()

    private val capableOfFetchingItems = object : CapableOfFetchingItems<Idea>(viewModelScope) {
        override suspend fun getItemsFromDatabase(position: Long, numItems: Int): List<Idea> {
            return repository.getIdeas(topic.topicId, position, numItems)
        }
    }

    override val itemsFetched: MutableLiveData<MutableList<Idea>>
        get() = capableOfFetchingItems.itemsFetched

    override fun fetchItemsIfNotFetched(position: Long, numItems: Int): Boolean {
        return capableOfFetchingItems.fetchItemsIfNotFetched(position, numItems)
    }
}