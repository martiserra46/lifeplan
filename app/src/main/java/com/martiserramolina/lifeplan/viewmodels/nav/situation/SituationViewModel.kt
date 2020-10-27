package com.martiserramolina.lifeplan.viewmodels.nav.situation

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.SituationRepository
import com.martiserramolina.lifeplan.repository.room.AppDb
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.viewmodels.abstracts.SituationRepositoryViewModel
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class SituationViewModel(application: Application) : SituationRepositoryViewModel(application) {

    companion object { private const val NUM_DAYS_TO_FETCH = 20 }

    val days = MutableLiveData<MutableList<Day>>().apply { value = mutableListOf() }

    private var lastDayPositionUsedToFetch: Int? = null

    init { fetchDaysFromPositionIfNotFetched(0) }

    fun fetchDaysFromPositionIfNotFetched(position: Int): Boolean {
        if (position == lastDayPositionUsedToFetch) return false
        lastDayPositionUsedToFetch = position
        viewModelScope.launch {
            days.value = days.value?.apply {
                addAll(repository.getDays(position, NUM_DAYS_TO_FETCH))
            }
        }
        return true
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SituationViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SituationViewModel(application) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}