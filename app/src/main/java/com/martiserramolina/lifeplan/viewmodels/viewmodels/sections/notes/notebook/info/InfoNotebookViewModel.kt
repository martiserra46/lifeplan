package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.notebook.info

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Note
import com.martiserramolina.lifeplan.repository.room.Notebook
import com.martiserramolina.lifeplan.viewmodels.interfaces.LoadListItemsViewModel
import com.martiserramolina.lifeplan.viewmodels.interfaces.DeleteItemViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.notebook.NotebookViewModel

class InfoNotebookViewModel(
    notebook: Notebook,
    application: Application
) : NotebookViewModel(notebook, application),
    LoadListItemsViewModel<Note>, DeleteItemViewModel {
    private val loadListItemsViewModel = object : LoadListItemsViewModel.Object<Note>() {
        override val coroutineScope get() = viewModelScope
        override suspend fun getItemsFromDatabase(position: Long, numItems: Int): List<Note> {
            return repository.getNotes(notebook.notebookId, position, numItems)
        }
    }

    private val deleteItemViewModel = object : DeleteItemViewModel.Object() {
        override val coroutineScope = viewModelScope
        override suspend fun deleteItemFromDatabase() {
            repository.deleteNotebook(notebook)
        }
    }

    override val itemsFetched get() = loadListItemsViewModel.itemsFetched
    override fun fetchItemsIfNotFetched(position: Long, numItems: Int) =
        loadListItemsViewModel.fetchItemsIfNotFetched(position, numItems)

    override val itemDeleted get() = deleteItemViewModel.itemDeleted
    override fun deleteItem() = deleteItemViewModel.deleteItem()
}