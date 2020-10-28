package com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day.edit

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.viewmodels.viewmodels.SituationRepositoryViewModel
import java.lang.IllegalArgumentException

class EditDayViewModel(
    val day: Day, application: Application
) : SituationRepositoryViewModel(application) {

    val dayEdited = MutableLiveData<Boolean>().apply { value = false }

    fun editDay() {
        viewModelScope.launch {
            repository.updateDay(day)
            dayEdited.value = true
        }
    }
}