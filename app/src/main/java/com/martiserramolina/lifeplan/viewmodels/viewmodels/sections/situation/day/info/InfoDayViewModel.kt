package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.situation.day.info

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.viewmodels.interfaces.DeleteItemViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.situation.day.DayViewModel

class InfoDayViewModel(
    day: Day,
    application: Application
) : DayViewModel(day, application), DeleteItemViewModel {
    private val deleteItemViewModel = object : DeleteItemViewModel.Object() {
        override val coroutineScope = viewModelScope
        override suspend fun deleteItemFromDatabase() {
            repository.deleteDay(day)
        }
    }
    override val itemDeleted get() = deleteItemViewModel.itemDeleted
    override fun deleteItem() = deleteItemViewModel.deleteItem()
}