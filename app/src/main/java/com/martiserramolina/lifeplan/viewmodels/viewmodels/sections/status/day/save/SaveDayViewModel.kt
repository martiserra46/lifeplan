package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.status.day.save

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.viewmodels.interfaces.SaveItemViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.status.day.DayViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class SaveDayViewModel(
    day: Day,
    application: Application
) : DayViewModel(day, application), SaveItemViewModel {
    private val saveItemViewModel = object : SaveItemViewModel.Object() {
        override val coroutineScope = viewModelScope
        override suspend fun saveItemToDatabase() {
            this@SaveDayViewModel.saveItemToDatabase()
        }
    }
    override val itemSaved get() = saveItemViewModel.itemSaved
    override fun saveItem() = saveItemViewModel.saveItem()

    abstract suspend fun saveItemToDatabase()
}