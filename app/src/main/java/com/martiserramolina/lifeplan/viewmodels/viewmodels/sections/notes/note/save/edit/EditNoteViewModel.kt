package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.note.save.edit

import android.app.Application
import com.martiserramolina.lifeplan.repository.room.Note
import com.martiserramolina.lifeplan.repository.room.Notebook
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.note.save.SaveNoteViewModel

class EditNoteViewModel(
    note: Note,
    notebook: Notebook,
    application: Application
) : SaveNoteViewModel(note, notebook, application) {
    override suspend fun saveItemToDatabase() {
        repository.updateNote(note)
    }
}