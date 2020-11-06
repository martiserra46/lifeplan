package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.notebook.info

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.PagedList
import com.martiserramolina.lifeplan.repository.room.Note
import com.martiserramolina.lifeplan.repository.room.Notebook
import com.martiserramolina.lifeplan.viewmodels.interfaces.DeleteItemViewModel
import com.martiserramolina.lifeplan.viewmodels.interfaces.LoadItemsViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.notebook.NotebookViewModel

class InfoNotebookViewModel(
    notebook: Notebook,
    application: Application
) : NotebookViewModel(notebook, application), LoadItemsViewModel<Note>, DeleteItemViewModel {
    override val items: LiveData<PagedList<Note>> = repository.getNotes(notebook.notebookId)

    private val deleteItemViewModel = object : DeleteItemViewModel.Object() {
        override val coroutineScope = viewModelScope
        override suspend fun deleteItemFromDatabase() {
            repository.deleteNotebook(notebook)
        }
    }

    override val itemDeleted get() = deleteItemViewModel.itemDeleted
    override fun deleteItem() = deleteItemViewModel.deleteItem()
}