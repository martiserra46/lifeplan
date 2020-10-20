package com.martiserramolina.lifeplan.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.martiserramolina.lifeplan.repository.model.Day
import com.martiserramolina.lifeplan.repository.model.Topic
import com.martiserramolina.lifeplan.repository.model.Life
import com.martiserramolina.lifeplan.repository.room.*

class Repository(context: Context) {
    val lifeRepository by lazy { LifeRepository(context) }
    val ideasRepository by lazy { IdeasRepository(context) }
    val dayRepository by lazy { SituationRepository(context) }
}

class LifeRepository(context: Context) {

    private val db by lazy { AppDb.getInstance(context) }
    private val daoLife by lazy { db.daoLifeDb() }

    fun getLife(): Life? {
        return daoLife.getLife()?.toLife()
    }

    fun insertLife(lifeDescription: Life) {
        daoLife.insertLife(lifeDescription.toLifeDb())
    }
}

class IdeasRepository(context: Context) {

    private val db by lazy { AppDb.getInstance(context) }
    private val daoTopic by lazy { db.daoTopicDb() }

    fun getTopics(): LiveData<List<Topic>> {
        return Transformations.map(daoTopic.getTopics()) { it.toListTopics() }
    }

    fun insertTopic(topic: Topic) {
        daoTopic.insertTopic(topic.toTopicDb())
    }
}

class SituationRepository(context: Context) {

    private val db by lazy { AppDb.getInstance(context) }
    private val daoDay by lazy { db.daoDayDb() }

    fun getDays(): LiveData<List<Pair<Long, Day>>> {
        return Transformations.map(daoDay.getDays()) { it.toListDays() }
    }

    fun insertDay(day: Day) {
        daoDay.insertDay(day.toDayDb())
    }
}