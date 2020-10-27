package com.martiserramolina.lifeplan.viewmodels.abstracts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.martiserramolina.lifeplan.repository.IdeasRepository
import com.martiserramolina.lifeplan.repository.LifeRepository
import com.martiserramolina.lifeplan.repository.Repository
import com.martiserramolina.lifeplan.repository.SituationRepository
import com.martiserramolina.lifeplan.repository.room.AppDb

abstract class RepositoryViewModel<T : Repository>(
    application: Application
): AndroidViewModel(application) {
    protected abstract val repository: T
}

abstract class LifeRepositoryViewModel(
    application: Application
) : RepositoryViewModel<LifeRepository>(application) {
    override val repository by lazy {
        LifeRepository(AppDb.getInstance(application.applicationContext).daoLife())
    }
}

abstract class IdeasRepositoryViewModel(
    application: Application
) : RepositoryViewModel<IdeasRepository>(application) {
    override val repository by lazy {
        IdeasRepository(AppDb.getInstance(application.applicationContext).daoIdeas())
    }
}

abstract class SituationRepositoryViewModel(
    application: Application
) : RepositoryViewModel<SituationRepository>(application) {
    override val repository by lazy {
        SituationRepository(AppDb.getInstance(application.applicationContext).daoSituation())
    }
}