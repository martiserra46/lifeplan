package com.martiserramolina.lifeplan.viewmodels.situation.day

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.repository.model.SituationDay
import java.lang.IllegalArgumentException

class SituationDayViewModel(val situationDay: SituationDay) : ViewModel() {

    class Factory(private val situationDay: SituationDay) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SituationDayViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SituationDayViewModel(situationDay) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}