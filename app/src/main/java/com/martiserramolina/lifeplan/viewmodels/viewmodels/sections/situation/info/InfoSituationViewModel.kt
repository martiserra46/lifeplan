package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.situation.info

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.viewmodels.interfaces.LoadListItemsViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.situation.SituationViewModel

class InfoSituationViewModel(
    application: Application
) : SituationViewModel(application), LoadListItemsViewModel<Day> {
    private val loadListItemsViewModel = object : LoadListItemsViewModel.Object<Day>() {
        override val coroutineScope get() = viewModelScope
        override suspend fun getItemsFromDatabase(position: Long, numItems: Int): List<Day> {
            return repository.getDays(position, numItems)
        }
    }

    override val itemsFetched get() = loadListItemsViewModel.itemsFetched
    override fun fetchItemsIfNotFetched(position: Long, numItems: Int): Boolean =
        loadListItemsViewModel.fetchItemsIfNotFetched(position, numItems)
}