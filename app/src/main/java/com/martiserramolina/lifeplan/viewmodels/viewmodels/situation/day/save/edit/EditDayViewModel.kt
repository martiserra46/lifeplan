package com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day.save.edit

import android.app.Application
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day.save.SaveDayViewModel

class EditDayViewModel(
    day: Day,
    application: Application
) : SaveDayViewModel(day, application) {
    override suspend fun saveDayToDatabase() {
        repository.updateDay(day)
    }
}