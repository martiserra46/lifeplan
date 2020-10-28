package com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day.info

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.viewmodels.viewmodels.SituationRepositoryViewModel
import java.lang.IllegalArgumentException

class InfoDayViewModel(val day: Day, application: Application) : SituationRepositoryViewModel(application) {

    val dayDeleted = MutableLiveData<Boolean>().apply { value = false }

    fun deleteDay() {
        viewModelScope.launch {
            repository.deleteDay(day)
            dayDeleted.value = true
        }
    }

    class Factory(
        private val day: Day, private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(InfoDayViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return InfoDayViewModel(day, application) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}