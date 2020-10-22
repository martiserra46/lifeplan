package com.martiserramolina.lifeplan.repository.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.martiserramolina.lifeplan.repository.enums.IdeaImportance
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
@Entity(tableName = "topic")
data class Topic(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "topic_id")
    var topicId: Long = 0,
    @ColumnInfo(name = "topic_text")
    var topicText: String = "",
    @ColumnInfo(name = "topic_num_ideas")
    var topicNumIdeas: Int = 0
) : Parcelable

@Parcelize
@Entity(tableName = "idea")
data class Idea(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idea_id")
    var ideaId: Long = 0,
    @ColumnInfo(name = "idea_topic_id")
    var ideaTopicId: Long,
    @ColumnInfo(name = "idea_title")
    var ideaTitle: String = "",
    @ColumnInfo(name = "idea_importance")
    var ideaImportance: IdeaImportance = IdeaImportance.NORMAL,
    @ColumnInfo(name = "idea_description")
    var ideaDescription: String = "",
    @ColumnInfo(name = "idea_last_time_modified")
    var ideaLastTimeModified: Date = Date()
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
