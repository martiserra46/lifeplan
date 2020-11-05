package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.status.day

import android.app.Application
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.status.StatusViewModel

abstract class DayViewModel(
    val day: Day,
    application: Application
) : StatusViewModel(application)