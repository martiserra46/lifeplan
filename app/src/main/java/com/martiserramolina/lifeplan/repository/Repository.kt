package com.martiserramolina.lifeplan.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.martiserramolina.lifeplan.repository.model.Topic
import com.martiserramolina.lifeplan.repository.model.YourLife
import com.martiserramolina.lifeplan.repository.room.*

class Repository(context: Context) {
    val yourLifeRepository by lazy { YourLifeRepository(context) }
    val ideasRepository by lazy { IdeasRepository(context) }
    val situationDayRepository by lazy { SituationDayRepository(context) }
}

class YourLifeRepository(context: Context) {

    private val db by lazy { AppDb.getInstance(context) }
    private val daoYourLife by lazy { db.daoYourLifeDb() }

    fun getYourLife(): YourLife? {
        return daoYourLife.getYourLife()?.toYourLife()
    }

    fun insertYourLife(lifeDescription: YourLife) {
        daoYourLife.insertYourLife(lifeDescription.toYourLifeDb())
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

class SituationDayRepository(context: Context) {

}