package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.situation.day.save.add

import android.app.Application
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.situation.day.save.SaveDayViewModel

class AddDayViewModel(
    application: Application
) : SaveDayViewModel(Day(), application) {
    override suspend fun saveDayToDatabase() {
        repository.insertDay(day)
    }
}