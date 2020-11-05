package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.notebook.save

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.martiserramolina.lifeplan.repository.room.Notebook
import com.martiserramolina.lifeplan.viewmodels.interfaces.SaveItemViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.notebook.NotebookViewModel

abstract class SaveNotebookViewModel(
    notebook: Notebook,
    application: Application
) : NotebookViewModel(notebook, application), SaveItemViewModel {
    private val saveItemViewModel = object : SaveItemViewModel.Object() {
        override val coroutineScope = viewModelScope
        override suspend fun saveItemToDatabase() {
            this@SaveNotebookViewModel.saveItemToDatabase()
        }
    }
    override val itemSaved = saveItemViewModel.itemSaved
    override fun saveItem() = saveItemViewModel.saveItem()

    abstract suspend fun saveItemToDatabase()
}