package com.martiserramolina.lifeplan.viewmodels.viewmodels.situation

import android.app.Application
import com.martiserramolina.lifeplan.repository.SituationRepository
import com.martiserramolina.lifeplan.repository.room.AppDb
import com.martiserramolina.lifeplan.viewmodels.viewmodels.RepositoryViewModel

abstract class SituationViewModel(
    application: Application
) : RepositoryViewModel<SituationRepository>(application) {
    override val repository by lazy {
        SituationRepository(AppDb.getInstance(application.applicationContext).daoSituation())
    }
}