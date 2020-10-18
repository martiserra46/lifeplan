package com.martiserramolina.lifeplan.viewmodels.situation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.lang.IllegalArgumentException

class SituationViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository(application.applicationContext)

    val situationDays = repository.situationDayRepository.getSituationDays()

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SituationViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SituationViewModel(application) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}