package com.martiserramolina.lifeplan.viewmodels.situation.day.edit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.repository.Repository
import com.martiserramolina.lifeplan.repository.model.SituationDay
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException
import java.util.*

class EditSituationDayViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository(application.applicationContext)

    lateinit var date: Date

    private val coroutineJob = Job()
    private val coroutineMainScope = CoroutineScope(Dispatchers.Main + coroutineJob)

    fun updateSituationDay(situationDay: SituationDay) {
        coroutineMainScope.launch {
            withContext(Dispatchers.IO) {
                repository.situationDayRepository
                    .insertSituationDay(situationDay)
            }
        }
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EditSituationDayViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EditSituationDayViewModel(application) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}