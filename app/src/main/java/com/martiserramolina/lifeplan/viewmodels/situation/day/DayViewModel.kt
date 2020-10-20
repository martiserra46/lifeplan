package com.martiserramolina.lifeplan.viewmodels.situation.day

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.repository.room.Day
import java.lang.IllegalArgumentException

class DayViewModel(val day: Day) : ViewModel() {

    class Factory(private val day: Day) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DayViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DayViewModel(day) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}