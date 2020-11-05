package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.note.info

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Note
import com.martiserramolina.lifeplan.repository.room.Notebook
import com.martiserramolina.lifeplan.viewmodels.interfaces.DeleteItemViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.note.NoteViewModel

class InfoNoteViewModel(
    note: Note,
    notebook: Notebook,
    application: Application
) : NoteViewModel(note, notebook, application), DeleteItemViewModel {
    private val deleteItemViewModel = object : DeleteItemViewModel.Object() {
        override val coroutineScope = viewModelScope
        override suspend fun deleteItemFromDatabase() {
            repository.deleteNote(note)
        }
    }
    override val itemDeleted get() = deleteItemViewModel.itemDeleted
    override fun deleteItem() = deleteItemViewModel.deleteItem()
}