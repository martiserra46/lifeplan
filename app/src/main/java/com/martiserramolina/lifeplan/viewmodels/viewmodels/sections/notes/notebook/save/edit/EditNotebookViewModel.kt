package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.notebook.save.edit

import android.app.Application
import com.martiserramolina.lifeplan.repository.room.Notebook
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.notebook.save.SaveNotebookViewModel

class EditNotebookViewModel(
    notebook: Notebook,
    application: Application
) : SaveNotebookViewModel(notebook, application) {
    override suspend fun saveItemToDatabase() {
        repository.updateNotebook(notebook)
    }
}