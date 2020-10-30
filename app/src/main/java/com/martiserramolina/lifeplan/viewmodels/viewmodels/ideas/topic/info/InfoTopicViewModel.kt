package com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.topic.info

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.capable_of_fetching_items.CapableOfFetchingItems
import com.martiserramolina.lifeplan.viewmodels.capable_of_fetching_items.CapableOfFetchingItemsI
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.topic.TopicViewModel
import kotlinx.coroutines.launch

class InfoTopicViewModel(
    topic: Topic,
    application: Application
) : TopicViewModel(topic, application),
    CapableOfFetchingItemsI<Idea> {

    private val capableOfFetchingItems = object : CapableOfFetchingItems<Idea>(viewModelScope) {
        override suspend fun getItemsFromDatabase(position: Long, numItems: Int): List<Idea> {
            return repository.getIdeas(topic.topicId, position, numItems)
        }
    }

    val topicDeleted = MutableLiveData<Boolean>().apply { value = false }

    override val itemsFetched: MutableLiveData<MutableList<Idea>>
        get() = capableOfFetchingItems.itemsFetched

    override fun fetchItemsIfNotFetched(position: Long, numItems: Int): Boolean {
        return capableOfFetchingItems.fetchItemsIfNotFetched(position, numItems)
    }

    fun deleteTopic() {
        viewModelScope.launch {
            repository.deleteTopic(topic)
            topicDeleted.value = true
        }
    }
}