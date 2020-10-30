package com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.info

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.viewmodels.capable_of_fetching_items.classes.CapableOfFetchingItems
import com.martiserramolina.lifeplan.viewmodels.capable_of_fetching_items.interfaces.CapableOfFetchingItemsI
import com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.SituationViewModel

class InfoSituationViewModel(
    application: Application
) : SituationViewModel(application),
    CapableOfFetchingItemsI<Day> {

    private val capableOfFetchingItems = object : CapableOfFetchingItems<Day>(viewModelScope) {
        override suspend fun getItemsFromDatabase(position: Long, numItems: Int): List<Day> {
            return repository.getDays(position, numItems)
        }
    }

    override val itemsFetched: MutableLiveData<MutableList<Day>>
        get() = capableOfFetchingItems.itemsFetched

    override fun fetchItemsIfNotFetched(position: Long, numItems: Int): Boolean {
        return capableOfFetchingItems.fetchItemsIfNotFetched(position, numItems)
    }
}