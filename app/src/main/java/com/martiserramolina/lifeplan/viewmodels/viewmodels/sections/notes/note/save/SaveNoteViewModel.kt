package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.note.save

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.martiserramolina.lifeplan.repository.room.Note
import com.martiserramolina.lifeplan.repository.room.Notebook
import com.martiserramolina.lifeplan.viewmodels.interfaces.SaveItemViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.note.NoteViewModel

abstract class SaveNoteViewModel(
    note: Note,
    notebook: Notebook,
    application: Application
) : NoteViewModel(note, notebook, application), SaveItemViewModel {
    private val saveItemViewModel = object : SaveItemViewModel.Object() {
        override val coroutineScope = viewModelScope
        override suspend fun saveItemToDatabase() {
            this@SaveNoteViewModel.saveItemToDatabase()
        }
    }
    override val itemSaved get() = saveItemViewModel.itemSaved
    override fun saveItem() = saveItemViewModel.saveItem()

    abstract suspend fun saveItemToDatabase()
}