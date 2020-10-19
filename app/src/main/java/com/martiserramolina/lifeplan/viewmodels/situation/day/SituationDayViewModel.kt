package com.martiserramolina.lifeplan.viewmodels.situation.day

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.repository.model.SituationDay
import java.lang.IllegalArgumentException

class SituationDayViewModel(
    val situationDayId: Long, val situationDay: SituationDay
) : ViewModel() {

    class Factory(
        private val situationDayId: Long, private val situationDay: SituationDay
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SituationDayViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SituationDayViewModel(situationDayId, situationDay) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}