package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes

import android.app.Application
import com.martiserramolina.lifeplan.repository.NotesRepository
import com.martiserramolina.lifeplan.repository.room.AppDb
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.RepositoryViewModel

abstract class NotesViewModel(
    application: Application
) : RepositoryViewModel<NotesRepository>(application) {
    override val repository by lazy {
        NotesRepository(AppDb.getInstance(application.applicationContext).daoNotes())
    }
}