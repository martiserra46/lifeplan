package com.martiserramolina.lifeplan.viewmodels.situation.day

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.repository.model.Day
import java.lang.IllegalArgumentException

class DayViewModel(
    val situationDayId: Long, val situationDay: Day
) : ViewModel() {

    class Factory(
        private val situationDayId: Long, private val situationDay: Day
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DayViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DayViewModel(situationDayId, situationDay) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}