package com.martiserramolina.lifeplan.repository

import android.content.Context
import com.martiserramolina.lifeplan.repository.model.YourLife
import com.martiserramolina.lifeplan.repository.room.AppDb
import com.martiserramolina.lifeplan.repository.room.YourLifeDb
import com.martiserramolina.lifeplan.repository.room.toYourLife
import com.martiserramolina.lifeplan.repository.room.toYourLifeDb

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

}

class SituationDayRepository(context: Context) {

}