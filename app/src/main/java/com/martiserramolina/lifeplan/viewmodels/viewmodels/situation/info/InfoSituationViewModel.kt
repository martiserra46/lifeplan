package com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.info

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.viewmodels.viewmodels.SituationRepositoryViewModel
import java.lang.IllegalArgumentException

class InfoSituationViewModel(application: Application) : SituationRepositoryViewModel(application) {

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
}