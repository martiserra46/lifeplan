package com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day.save.add

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.viewmodels.enums.SaveOperation
import com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day.DayViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day.save.SaveDayViewModel
import kotlinx.coroutines.launch

class AddDayViewModel(
    application: Application
) : SaveDayViewModel(Day(), application, SaveOperation.ADD)