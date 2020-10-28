package com.martiserramolina.lifeplan.viewmodels.viewmodels

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