package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.status.day.save.edit

import android.app.Application
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.status.day.save.SaveDayViewModel

class EditDayViewModel(
    day: Day,
    application: Application
) : SaveDayViewModel(day, application) {
    override suspend fun saveItemToDatabase() {
        repository.updateDay(day)
    }
}