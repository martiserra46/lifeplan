package com.martiserramolina.lifeplan.repository.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.martiserramolina.lifeplan.repository.enums.IdeaImportance
import com.martiserramolina.lifeplan.repository.enums.DaySatisfaction
import com.martiserramolina.lifeplan.repository.model.Idea
import com.martiserramolina.lifeplan.repository.model.Day
import com.martiserramolina.lifeplan.repository.model.Topic
import com.martiserramolina.lifeplan.repository.model.Life
import java.util.*

@Entity(tableName = "life")
data class LifeDb(
    @PrimaryKey
    @ColumnInfo(name = "life_id")
    val lifeId: Long = 0,
    @ColumnInfo(name = "life_text")
    val lifeText: String = ""
)

fun LifeDb.toLife(): Life {
    return Life(lifeText)
}

fun Life.toLifeDb(): LifeDb {
    return LifeDb(lifeText = text)
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

@Entity(tableName = "day")
data class DayDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "day_id")
    val dayId: Long = 0,
    @ColumnInfo(name = "day_date")
    var dayDate: Date = Date(),
    @ColumnInfo(name = "day_text")
    var dayText: String = "",
    @ColumnInfo(name = "day_satisfaction")
    var daySatisfaction: DaySatisfaction = DaySatisfaction.NORMAL,
)

fun DayDb.toDay(): Pair<Long, Day> {
    return dayId to Day(dayDate, dayText, daySatisfaction)
}

fun List<DayDb>.toListDays(): List<Pair<Long, Day>> {
    return map { it.toDay() }
}

fun Day.toDayDb(id: Long = 0): DayDb {
    return DayDb(id, date, text, satisfaction)
}

fun List<Day>.toListDaysDb(): List<DayDb> {
    return map { it.toDayDb() }
}