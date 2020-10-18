package com.martiserramolina.lifeplan.viewmodels.situation.day.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.repository.Repository
import com.martiserramolina.lifeplan.repository.enums.SituationDaySatisfaction
import com.martiserramolina.lifeplan.repository.model.SituationDay
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException
import java.util.*

class AddSituationDayViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository(application.applicationContext)

    lateinit var date: Date
    lateinit var description: String
    lateinit var satisfaction: SituationDaySatisfaction

    private val coroutineJob = Job()
    private val coroutineMainScope = CoroutineScope(Dispatchers.Main + coroutineJob)

    fun insertSituationDay() {
        coroutineMainScope.launch {
            withContext(Dispatchers.IO) {
                repository.situationDayRepository
                    .insertSituationDay(SituationDay(date, description, satisfaction))
            }
        }
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddSituationDayViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AddSituationDayViewModel(application) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}