package com.martiserramolina.lifeplan.viewmodels.situation.day.edit

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.SituationRepository
import com.martiserramolina.lifeplan.repository.room.AppDb
import com.martiserramolina.lifeplan.repository.room.Day
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException
import java.util.*

class EditDayViewModel(var day: Day, application: Application) : AndroidViewModel(application) {

    private val repository by lazy {
        SituationRepository(AppDb.getInstance(application.applicationContext).daoSituation())
    }

    val dayUpdated = MutableLiveData<Boolean>().apply { value = false }

    fun updateDay(day: Day) {
        viewModelScope.launch {
            repository.updateDay(day)
            dayUpdated.value = true
        }
    }

    class Factory(
        private val day: Day, private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EditDayViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EditDayViewModel(day, application) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}