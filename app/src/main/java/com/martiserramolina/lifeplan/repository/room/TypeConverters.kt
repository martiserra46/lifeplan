package com.martiserramolina.lifeplan.repository.room

import androidx.room.TypeConverter
import com.martiserramolina.lifeplan.repository.enums.NoteImportance
import com.martiserramolina.lifeplan.repository.enums.DaySatisfaction
import java.util.*

class NoteImportanceConverter {
    @TypeConverter
    fun fromNoteImportanceToInt(noteImportance: NoteImportance): Int {
        return noteImportance.ordinal
    }
    @TypeConverter
    fun fromIntToNoteImportance(int: Int): NoteImportance {
        return NoteImportance.values()[int]
    }
}

class DaySatisfactionConverter {
    @TypeConverter
    fun fromDaySatisfactionToInt(daySatisfaction: DaySatisfaction): Int {
        return daySatisfaction.ordinal
    }

    @TypeConverter
    fun fromIntToDaySatisfaction(int: Int): DaySatisfaction {
        return DaySatisfaction.values()[int]
    }
}

class DateConverter {
    @TypeConverter
    fun fromDateToLong(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun fromLongToDate(time: Long): Date {
        return Date().apply { this.time = time }
    }
}