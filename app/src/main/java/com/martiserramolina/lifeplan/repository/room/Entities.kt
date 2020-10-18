package com.martiserramolina.lifeplan.repository.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.martiserramolina.lifeplan.repository.enums.IdeaImportance
import com.martiserramolina.lifeplan.repository.enums.SituationDaySatisfaction
import com.martiserramolina.lifeplan.repository.model.Idea
import com.martiserramolina.lifeplan.repository.model.SituationDay
import com.martiserramolina.lifeplan.repository.model.Topic
import com.martiserramolina.lifeplan.repository.model.YourLife
import java.util.*

@Entity(tableName = "your_life")
data class YourLifeDb(
    @PrimaryKey
    @ColumnInfo(name = "your_life_id")
    val yourLifeId: Long = 0,
    @ColumnInfo(name = "your_life_text")
    val yourLifeText: String = ""
)

fun YourLifeDb.toYourLife(): YourLife {
    return YourLife(yourLifeText)
}

fun YourLife.toYourLifeDb(): YourLifeDb {
    return YourLifeDb(yourLifeText = text)
}

@Entity(tableName = "topic")
data class TopicDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "topic_id")
    val topicId: Long = 0,
    @ColumnInfo(name = "topic_name")
    val topicName: String = ""
)

fun TopicDb.toTopic(): Topic {
    return Topic(topicName)
}

fun List<TopicDb>.toListTopics(): List<Topic> {
    return map { it.toTopic() }
}

fun Topic.toTopicDb(): TopicDb {
    return TopicDb(0, name)
}

fun List<Topic>.toListTopicsDb(): List<TopicDb> {
    return map { it.toTopicDb() }
}

@Entity(tableName = "idea")
data class IdeaDb(
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

fun IdeaDb.toIdea(): Idea {
    return Idea(ideaText, ideaImportance, ideaLastTimeModified)
}

fun List<IdeaDb>.toListIdeas(): List<Idea> {
    return map { it.toIdea() }
}

fun Idea.toIdeaDb(topicId: Long): IdeaDb {
    return IdeaDb(0, topicId, text, importance, lastTimeModified)
}

fun List<Idea>.toListIdeasDb(topicId: Long): List<IdeaDb> {
    return map { it.toIdeaDb(topicId) }
}

@Entity(tableName = "situation_day")
data class SituationDayDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "situation_day_id")
    val situationDayId: Long = 0,
    @ColumnInfo(name = "situation_day_date")
    var situationDayDate: Date = Date(),
    @ColumnInfo(name = "situation_day_text")
    var situationDayText: String = "",
    @ColumnInfo(name = "situation_day_satisfaction")
    var situationDaySatisfaction: SituationDaySatisfaction = SituationDaySatisfaction.NORMAL,
)

fun SituationDayDb.toSituationDay(): SituationDay {
    return SituationDay(situationDayDate, situationDayText, situationDaySatisfaction)
}

fun List<SituationDayDb>.toListSituationDays(): List<SituationDay> {
    return map { it.toSituationDay() }
}

fun SituationDay.toSituationDayDb(): SituationDayDb {
    return SituationDayDb(0, date, text, satisfaction)
}

fun List<SituationDay>.toListSituationDaysDb(): List<SituationDayDb> {
    return map { it.toSituationDayDb() }
}