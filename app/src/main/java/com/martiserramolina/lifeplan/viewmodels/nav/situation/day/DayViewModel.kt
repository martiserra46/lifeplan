package com.martiserramolina.lifeplan.viewmodels.nav.situation.day

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.SituationRepository
import com.martiserramolina.lifeplan.repository.room.AppDb
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.viewmodels.abstracts.SituationRepositoryViewModel
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class DayViewModel(val day: Day, application: Application) : SituationRepositoryViewModel(application) {

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
            if (modelClass.isAssignableFrom(DayViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DayViewModel(day, application) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}