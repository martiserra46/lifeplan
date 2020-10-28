package com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day.edit

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day.DayViewModel
import kotlinx.coroutines.launch

class EditDayViewModel(
    day: Day,
    application: Application
) : DayViewModel(day, application) {
    val dayEdited = MutableLiveData<Boolean>().apply { value = false }
    fun editDay() {
        viewModelScope.launch {
            repository.updateDay(day)
            dayEdited.value = true
        }
    }
}