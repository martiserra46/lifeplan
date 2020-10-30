package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.life

import android.app.Application
import com.martiserramolina.lifeplan.repository.LifeRepository
import com.martiserramolina.lifeplan.repository.room.AppDb
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.RepositoryViewModel

abstract class LifeViewModel(
    application: Application
) : RepositoryViewModel<LifeRepository>(application) {
    override val repository by lazy {
        LifeRepository(AppDb.getInstance(application.applicationContext).daoLife())
    }
}