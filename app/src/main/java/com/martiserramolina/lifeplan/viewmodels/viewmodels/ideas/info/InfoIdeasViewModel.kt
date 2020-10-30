package com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.info

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.utils.classes.CapableOfFetchingItems
import com.martiserramolina.lifeplan.viewmodels.utils.interfaces.CapableOfFetchingItemsI
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.IdeasViewModel
import kotlinx.coroutines.launch

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