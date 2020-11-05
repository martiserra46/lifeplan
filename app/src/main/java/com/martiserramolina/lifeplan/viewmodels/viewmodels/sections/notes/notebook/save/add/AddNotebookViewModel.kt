package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.notebook.save.add

import android.app.Application
import com.martiserramolina.lifeplan.repository.room.Notebook
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.notebook.save.SaveNotebookViewModel

class AddNotebookViewModel(
    application: Application
) : SaveNotebookViewModel(Notebook(), application) {
    override suspend fun saveItemToDatabase() {
        repository.insertNotebook(notebook)
    }
}