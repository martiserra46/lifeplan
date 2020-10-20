package com.martiserramolina.lifeplan.repository.room

import androidx.room.TypeConverter
import com.martiserramolina.lifeplan.repository.enums.IdeaImportance
import com.martiserramolina.lifeplan.repository.enums.DaySatisfaction
import java.util.*

class IdeaImportanceConverterDb {
    @TypeConverter
    fun fromIdeaImportanceToInt(ideaImportance: IdeaImportance): Int {
        return ideaImportance.ordinal
    }
    @TypeConverter
    fun fromIntToIdeaImportance(int: Int): IdeaImportance {
        return IdeaImportance.values()[int]
    }
}

class DaySatisfactionConverterDb {
    @TypeConverter
    fun fromSituationDaySatisfactionToInt(situationDaySatisfaction: DaySatisfaction): Int {
        return situationDaySatisfaction.ordinal
    }

    @TypeConverter
    fun fromIntToSituationDaySatisfaction(int: Int): DaySatisfaction {
        return DaySatisfaction.values()[int]
    }
}

class DateConverterDb {
    @TypeConverter
    fun fromDateToLong(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun fromLongToDate(time: Long): Date {
        return Date().apply { this.time = time }
    }
}