package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas

import android.app.Application
import com.martiserramolina.lifeplan.repository.IdeasRepository
import com.martiserramolina.lifeplan.repository.room.AppDb
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.RepositoryViewModel

abstract class IdeasViewModel(
    application: Application
) : RepositoryViewModel<IdeasRepository>(application) {
    override val repository by lazy {
        IdeasRepository(AppDb.getInstance(application.applicationContext).daoIdeas())
    }
}