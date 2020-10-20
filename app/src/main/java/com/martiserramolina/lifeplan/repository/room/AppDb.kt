package com.martiserramolina.lifeplan.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        Life::class,
        Topic::class,
        Idea::class,
        Day::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    IdeaImportanceConverter::class,
    DaySatisfactionConverter::class,
    DateConverter::class
)
abstract class AppDb : RoomDatabase() {
    companion object {
        private lateinit var INSTANCE: AppDb
        private const val DATABASE_NAME = "app_db"
        fun getInstance(context: Context): AppDb {
            if (::INSTANCE.isInitialized) return INSTANCE
            return synchronized(this) {
                if (::INSTANCE.isInitialized) INSTANCE
                else Room.databaseBuilder(
                    context.applicationContext,
                    AppDb::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration().build().also { INSTANCE = it }
            }
        }
    }

    abstract fun daoLifeDb(): DaoLife

    abstract fun daoTopicDb(): DaoIdeas

    abstract fun daoIdeaDb(): DaoIdea

    abstract fun daoDayDb(): DaoSituation
}