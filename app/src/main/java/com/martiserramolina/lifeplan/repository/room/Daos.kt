package com.martiserramolina.lifeplan.repository.room

import androidx.room.*

@Dao
interface DaoLifeDb {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLife(lifeDb: LifeDb): Long

    @Query("SELECT * FROM life LIMIT 1")
    suspend fun getLife(): LifeDb?
}

@Dao
interface DaoTopicDb {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopic(topicDb: TopicDb): Long

    @Query("SELECT * FROM topic ORDER BY topic_id DESC")
    suspend fun getTopics(): List<TopicDb>

    @Query("SELECT * FROM topic WHERE topic_id = :topicId")
    suspend fun getTopic(topicId: Long): TopicDb
}

@Dao
interface DaoIdeaDb {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIdea(ideaDb: IdeaDb): Long

    @Query("SELECT * FROM idea ORDER BY idea_id DESC")
    suspend fun getIdeas(): List<IdeaDb>

    @Query("SELECT * FROM idea WHERE idea_id = :ideaId")
    suspend fun getIdea(ideaId: Long): IdeaDb
}

@Dao
interface DaoDayDb {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDay(dayDb: DayDb): Long

    @Update
    suspend fun updateDay(dayDb: DayDb)

    @Query("SELECT * FROM day ORDER BY day_id DESC")
    suspend fun getDays(): List<DayDb>

    @Query("SELECT * FROM day WHERE day_id = :dayId")
    suspend fun getDay(dayId: Long): DayDb
}