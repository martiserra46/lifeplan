package com.martiserramolina.lifeplan.viewmodels.situation.day

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.repository.model.Day
import java.lang.IllegalArgumentException

class DayViewModel(
    val dayId: Long, val day: Day
) : ViewModel() {

    class Factory(
        private val dayId: Long, private val day: Day
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DayViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DayViewModel(dayId, day) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}