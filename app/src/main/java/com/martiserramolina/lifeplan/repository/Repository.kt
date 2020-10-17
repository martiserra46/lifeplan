package com.martiserramolina.lifeplan.repository

import android.content.Context
import com.martiserramolina.lifeplan.repository.model.YourLife
import com.martiserramolina.lifeplan.repository.room.AppDb
import com.martiserramolina.lifeplan.repository.room.YourLifeDb
import com.martiserramolina.lifeplan.repository.room.toYourLife

class Repository(context: Context) {
    val yourLifeRepository by lazy { YourLifeRepository(context) }
    val ideasRepository by lazy { IdeasRepository(context) }
    val situationDayRepository by lazy { SituationDayRepository(context) }
}

class YourLifeRepository(context: Context) {

    private val db by lazy { AppDb.getInstance(context) }
    private val daoYourLife by lazy { db.daoYourLifeDb() }

    suspend fun getYourLife(): YourLife? {
        return daoYourLife.getYourLife()?.toYourLife()
    }

    suspend fun insertYourLife(lifeDescription: YourLife) {
        daoYourLife.insertYourLife(YourLifeDb(1, lifeDescription.text))
    }
}

class IdeasRepository(context: Context) {

}

class SituationDayRepository(context: Context) {

}