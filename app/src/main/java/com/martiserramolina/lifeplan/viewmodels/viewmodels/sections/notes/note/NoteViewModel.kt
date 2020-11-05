package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.note

import android.app.Application
import com.martiserramolina.lifeplan.repository.room.Note
import com.martiserramolina.lifeplan.repository.room.Notebook
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.NotesViewModel

abstract class NoteViewModel(
    val note: Note,
    val notebook: Notebook,
    application: Application
): NotesViewModel(application)