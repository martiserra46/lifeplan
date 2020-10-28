package com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day.save

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.viewmodels.enums.SaveOperation
import com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day.DayViewModel
import kotlinx.coroutines.launch

abstract class SaveDayViewModel(
    day: Day,
    application: Application,
    private val saveOperation: SaveOperation
) : DayViewModel(day, application) {
    val daySaved = MutableLiveData<Boolean>().apply { value = false }
    fun saveDay() {
        viewModelScope.launch {
            when (saveOperation) {
                SaveOperation.ADD -> repository.insertDay(day)
                SaveOperation.EDIT -> repository.updateDay(day)
            }
            daySaved.value = true
        }
    }
}