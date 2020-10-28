package com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day.info

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day.DayViewModel
import kotlinx.coroutines.launch

class InfoDayViewModel(
    override val day: Day,
    application: Application
) : DayViewModel(application) {
    val dayDeleted = MutableLiveData<Boolean>().apply { value = false }
    fun deleteDay() {
        viewModelScope.launch {
            repository.deleteDay(day)
            dayDeleted.value = true
        }
    }
}