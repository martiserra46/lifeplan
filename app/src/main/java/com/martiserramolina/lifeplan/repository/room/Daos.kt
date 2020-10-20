package com.martiserramolina.lifeplan.repository.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DaoYourLifeDb {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertYourLife(yourLifeDb: YourLifeDb): Long

    @Query("SELECT * FROM your_life LIMIT 1")
    fun getYourLife(): YourLifeDb?
}

@Dao
interface DaoTopicDb {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopic(topicDb: TopicDb): Long

    @Query("SELECT * FROM topic ORDER BY topic_id DESC")
    fun getTopics(): LiveData<List<TopicDb>>

    @Query("SELECT * FROM topic WHERE topic_id = :topicId")
    fun getTopic(topicId: Long): TopicDb
}

@Dao
interface DaoIdeaDb {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIdea(ideaDb: IdeaDb): Long

    @Query("SELECT * FROM idea ORDER BY idea_id DESC")
    fun getIdeas(): LiveData<List<IdeaDb>>

    @Query("SELECT * FROM idea WHERE idea_id = :ideaId")
    fun getIdea(ideaId: Long): IdeaDb
}

@Dao
interface DaoDayDb {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDay(dayDb: DayDb): Long

    @Update
    fun updateDay(dayDb: DayDb)

    @Query("SELECT * FROM day ORDER BY day_id DESC")
    fun getDays(): LiveData<List<DayDb>>

    @Query("SELECT * FROM day WHERE day_id = :dayId")
    fun getDay(dayId: Long): DayDb
}