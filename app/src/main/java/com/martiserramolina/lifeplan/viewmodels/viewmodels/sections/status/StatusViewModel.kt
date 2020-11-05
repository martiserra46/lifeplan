package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.status

import android.app.Application
import com.martiserramolina.lifeplan.repository.StatusRepository
import com.martiserramolina.lifeplan.repository.room.AppDb
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.RepositoryViewModel

abstract class StatusViewModel(
    application: Application
) : RepositoryViewModel<StatusRepository>(application) {
    override val repository by lazy {
        StatusRepository(AppDb.getInstance(application.applicationContext).daoStatus())
    }
}