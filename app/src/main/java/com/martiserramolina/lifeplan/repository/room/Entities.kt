package com.martiserramolina.lifeplan.repository.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.martiserramolina.lifeplan.repository.enums.IdeaImportance
import com.martiserramolina.lifeplan.repository.enums.DaySatisfaction
import java.util.*

@Entity(tableName = "life")
data class Life(
    @PrimaryKey
    @ColumnInfo(name = "life_id")
    val lifeId: Long = 0,
    @ColumnInfo(name = "life_text")
    val lifeText: String = ""
)

@Entity(tableName = "topic")
data class Topic(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "topic_id")
    val topicId: Long = 0,
    @ColumnInfo(name = "topic_text")
    val topicText: String = "",
    @ColumnInfo(name = "topic_num_ideas")
    var topicNumIdeas: Int = 0
)

@Entity(tableName = "idea")
data class Idea(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idea_id")
    val ideaId: Long = 0,
    @ColumnInfo(name = "idea_topic_id")
    val ideaTopicId: Long,
    @ColumnInfo(name = "idea_text")
    var ideaText: String = "",
    @ColumnInfo(name = "idea_importance")
    var ideaImportance: IdeaImportance = IdeaImportance.NORMAL,
    @ColumnInfo(name = "idea_last_time_modified")
    var ideaLastTimeModified: Date = Date()
)

@Entity(tableName = "day")
data class Day(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "day_id")
    val dayId: Long = 0,
    @ColumnInfo(name = "day_date")
    var dayDate: Date = Date(),
    @ColumnInfo(name = "day_text")
    var dayText: String = "",
    @ColumnInfo(name = "day_satisfaction")
    var daySatisfaction: DaySatisfaction = DaySatisfaction.NORMAL
)
