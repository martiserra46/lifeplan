package com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day.save.edit

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.viewmodels.enums.SaveOperation
import com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day.DayViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day.save.SaveDayViewModel
import kotlinx.coroutines.launch

class EditDayViewModel(
    day: Day,
    application: Application
) : SaveDayViewModel(day, application, SaveOperation.EDIT)