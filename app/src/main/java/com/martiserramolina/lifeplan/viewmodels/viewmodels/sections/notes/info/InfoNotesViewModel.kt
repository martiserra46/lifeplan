package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.info

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Notebook
import com.martiserramolina.lifeplan.viewmodels.interfaces.LoadListItemsViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.NotesViewModel

class InfoNotesViewModel(
    application: Application
) : NotesViewModel(application), LoadListItemsViewModel<Notebook> {
    private val loadListItemsViewModel = object : LoadListItemsViewModel.Object<Notebook>() {
        override val coroutineScope get() = viewModelScope
        override suspend fun getItemsFromDatabase(position: Long, numItems: Int): List<Notebook> {
            return repository.getNotebooks(position, numItems)
        }
    }

    override val itemsFetched get() = loadListItemsViewModel.itemsFetched
    override fun fetchItemsIfNotFetched(position: Long, numItems: Int): Boolean =
        loadListItemsViewModel.fetchItemsIfNotFetched(position, numItems)
}