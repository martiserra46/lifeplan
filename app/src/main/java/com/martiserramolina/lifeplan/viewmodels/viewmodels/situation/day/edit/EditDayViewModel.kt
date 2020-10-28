package com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day.edit

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day.DayViewModel
import kotlinx.coroutines.launch

class EditDayViewModel(
    override var day: Day,
    application: Application
) : DayViewModel(application) {
    val dayEdited = MutableLiveData<Boolean>().apply { value = false }
    fun editDay() {
        viewModelScope.launch {
            repository.updateDay(day)
            dayEdited.value = true
        }
    }
}