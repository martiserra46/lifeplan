package com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day

import android.app.Application
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.SituationViewModel

abstract class DayViewModel(
    protected val day: Day,
    application: Application
) : SituationViewModel(application)