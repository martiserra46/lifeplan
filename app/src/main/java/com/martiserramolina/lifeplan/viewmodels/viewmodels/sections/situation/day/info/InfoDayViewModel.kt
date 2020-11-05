package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.situation.day.info

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.viewmodels.interfaces.InfoItemViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.situation.day.DayViewModel
import kotlinx.coroutines.launch

class InfoDayViewModel(
    day: Day,
    application: Application
) : DayViewModel(day, application), InfoItemViewModel {
    private val infoItemViewModel = object : InfoItemViewModel.Object() {
        override val coroutineScope = viewModelScope
        override suspend fun deleteItemFromDatabase() {
            repository.deleteDay(day)
        }
    }
    override val itemDeleted get() = infoItemViewModel.itemDeleted
    override fun deleteItem() = infoItemViewModel.deleteItem()
}