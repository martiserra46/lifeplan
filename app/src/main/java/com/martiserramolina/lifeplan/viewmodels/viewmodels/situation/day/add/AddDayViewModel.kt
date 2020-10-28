package com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day.add

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day.DayViewModel
import kotlinx.coroutines.launch

class AddDayViewModel(application: Application) : DayViewModel(application) {
    override var day = Day()
    val dayAdded = MutableLiveData<Boolean>().apply { value = false }
    fun addDay() {
        viewModelScope.launch {
            repository.insertDay(day)
            dayAdded.value = true
        }
    }
}