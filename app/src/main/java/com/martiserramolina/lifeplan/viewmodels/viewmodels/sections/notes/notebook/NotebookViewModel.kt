package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.notebook

import android.app.Application
import com.martiserramolina.lifeplan.repository.room.Notebook
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.NotesViewModel

abstract class NotebookViewModel(
    val notebook: Notebook,
    application: Application
) : NotesViewModel(application)