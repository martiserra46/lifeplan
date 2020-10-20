package com.martiserramolina.lifeplan.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        YourLifeDb::class,
        TopicDb::class,
        IdeaDb::class,
        DayDb::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    IdeaImportanceConverterDb::class,
    DaySatisfactionConverterDb::class,
    DateConverterDb::class
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

    abstract fun daoYourLifeDb(): DaoYourLifeDb

    abstract fun daoTopicDb(): DaoTopicDb

    abstract fun daoIdeaDb(): DaoIdeaDb

    abstract fun daoDayDb(): DaoDayDb
}