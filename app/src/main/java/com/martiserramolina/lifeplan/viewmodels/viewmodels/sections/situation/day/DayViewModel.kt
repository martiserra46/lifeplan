package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.situation.day

import android.app.Application
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.situation.SituationViewModel

abstract class DayViewModel(
    val day: Day,
    application: Application
) : SituationViewModel(application)