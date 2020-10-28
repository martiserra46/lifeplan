package com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day.save.add

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.viewmodels.viewmodels.SituationRepositoryViewModel
import java.lang.IllegalArgumentException

class AddDayViewModel(application: Application) : SituationRepositoryViewModel(application) {

    val day = Day()

    val dayAdded = MutableLiveData<Boolean>().apply { value = false }

    fun addDay() {
        viewModelScope.launch {
            repository.insertDay(day)
            dayAdded.value = true
        }
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddDayViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AddDayViewModel(application) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}