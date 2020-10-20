package com.martiserramolina.lifeplan.viewmodels.situation.day.edit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.repository.Repository
import com.martiserramolina.lifeplan.repository.model.Day
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException
import java.util.*

class EditDayViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository(application.applicationContext)

    lateinit var date: Date

    private val coroutineJob = Job()
    private val coroutineMainScope = CoroutineScope(Dispatchers.Main + coroutineJob)

    fun updateDay(day: Day) {
        coroutineMainScope.launch {
            withContext(Dispatchers.IO) {
                repository.dayRepository
                    .insertDay(day)
            }
        }
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EditDayViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EditDayViewModel(application) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}