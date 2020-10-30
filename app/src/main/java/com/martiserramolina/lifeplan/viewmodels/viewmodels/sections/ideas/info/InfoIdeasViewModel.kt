package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.info

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.capable_of_fetching_items.CapableOfFetchingItems
import com.martiserramolina.lifeplan.viewmodels.capable_of_fetching_items.CapableOfFetchingItemsI
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.IdeasViewModel

class InfoIdeasViewModel(
    application: Application
) : IdeasViewModel(application),
    CapableOfFetchingItemsI<Topic> {

    private val capableOfFetchingItems = object : CapableOfFetchingItems<Topic>(viewModelScope) {
        override suspend fun getItemsFromDatabase(position: Long, numItems: Int): List<Topic> {
            return repository.getTopics(position, numItems)
        }
    }

    override val itemsFetched: MutableLiveData<MutableList<Topic>>
        get() = capableOfFetchingItems.itemsFetched

    override fun fetchItemsIfNotFetched(position: Long, numItems: Int): Boolean {
        return capableOfFetchingItems.fetchItemsIfNotFetched(position, numItems)
    }
}