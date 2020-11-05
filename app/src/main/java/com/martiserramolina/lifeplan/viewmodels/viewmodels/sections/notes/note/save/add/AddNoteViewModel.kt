package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.note.save.add

import android.app.Application
import com.martiserramolina.lifeplan.repository.room.Note
import com.martiserramolina.lifeplan.repository.room.Notebook
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.note.save.SaveNoteViewModel

class AddNoteViewModel(
    notebook: Notebook,
    application: Application
) : SaveNoteViewModel(Note(), notebook, application) {
    override suspend fun saveItemToDatabase() {
        repository.insertNote(note)
    }
}