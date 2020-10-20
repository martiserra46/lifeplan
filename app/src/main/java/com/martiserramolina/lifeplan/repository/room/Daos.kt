package com.martiserramolina.lifeplan.repository.room

import androidx.room.*

@Dao
interface DaoLifeDb {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLife(life: Life): Long

    @Query("SELECT * FROM life LIMIT 1")
    suspend fun getLife(): Life?
}

@Dao
interface DaoTopicDb {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopic(topic: Topic): Long

    @Query("SELECT * FROM topic ORDER BY topic_id DESC")
    suspend fun getTopics(): List<Topic>

    @Query("SELECT * FROM topic WHERE topic_id = :topicId")
    suspend fun getTopic(topicId: Long): Topic
}

@Dao
interface DaoIdeaDb {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIdea(idea: Idea): Long

    @Query("SELECT * FROM idea ORDER BY idea_id DESC")
    suspend fun getIdeas(): List<Idea>

    @Query("SELECT * FROM idea WHERE idea_id = :ideaId")
    suspend fun getIdea(ideaId: Long): Idea
}

@Dao
interface DaoDayDb {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDay(day: Day): Long

    @Update
    suspend fun updateDay(day: Day)

    @Query("SELECT * FROM day ORDER BY day_id DESC")
    suspend fun getDays(): List<Day>

    @Query("SELECT * FROM day WHERE day_id = :dayId")
    suspend fun getDay(dayId: Long): Day
}