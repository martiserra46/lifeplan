package com.martiserramolina.lifeplan.viewmodels.situation

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.SituationRepository
import com.martiserramolina.lifeplan.repository.room.AppDb
import com.martiserramolina.lifeplan.repository.room.Day
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class SituationViewModel(application: Application) : AndroidViewModel(application) {

    private val repository by lazy {
        SituationRepository(AppDb.getInstance(application.applicationContext).daoSituation())
    }

    val days = MutableLiveData<List<Day>>()

    init {
        viewModelScope.launch {
            days.value = repository.getDays()
        }
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