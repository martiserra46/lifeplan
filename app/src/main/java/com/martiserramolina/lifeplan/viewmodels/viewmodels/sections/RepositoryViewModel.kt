package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.martiserramolina.lifeplan.repository.Repository

abstract class RepositoryViewModel<T : Repository>(
    application: Application
): AndroidViewModel(application) {
    protected abstract val repository: T
}