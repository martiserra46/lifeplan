package com.martiserramolina.lifeplan.repository.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.martiserramolina.lifeplan.repository.enums.NoteImportance
import com.martiserramolina.lifeplan.repository.enums.DaySatisfaction
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "life")
data class Life(
    @PrimaryKey
    @ColumnInfo(name = "life_id")
    var lifeId: Long = 0,
    @ColumnInfo(name = "life_text")
    var lifeText: String = ""
) : Parcelable

@Parcelize
@Entity(tableName = "notebook")
data class Notebook(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "notebook_id")
    var notebookId: Long = 0,
    @ColumnInfo(name = "notebook_text")
    var notebookText: String = "",
    @ColumnInfo(name = "notebook_num_notes")
    var notebookNumNotes: Int = 0
) : Parcelable

@Parcelize
@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    var noteId: Long = 0,
    @ColumnInfo(name = "note_notebook_id")
    var noteNotebookId: Long = 0,
    @ColumnInfo(name = "note_title")
    var noteTitle: String = "",
    @ColumnInfo(name = "note_importance")
    var noteImportance: NoteImportance = NoteImportance.NORMAL,
    @ColumnInfo(name = "note_description")
    var noteDescription: String = "",
    @ColumnInfo(name = "note_last_time_modified")
    var noteLastTimeModified: Date = Date()
) : Parcelable

@Parcelize
@Entity(tableName = "day")
data class Day(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "day_id")
    var dayId: Long = 0,
    @ColumnInfo(name = "day_date")
    var dayDate: Date = Date(),
    @ColumnInfo(name = "day_text")
    var dayText: String = "",
    @ColumnInfo(name = "day_satisfaction")
    var daySatisfaction: DaySatisfaction = DaySatisfaction.NORMAL
) : Parcelable
