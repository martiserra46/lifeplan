package com.martiserramolina.lifeplan.repository.room

import androidx.room.*

@Dao
interface DaoLife {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLife(life: Life): Long

    @Query("SELECT * FROM life LIMIT 1")
    suspend fun getLife(): Life?
}

@Dao
interface DaoIdeas {
    /* TOPIC */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopic(topic: Topic): Long

    @Update
    suspend fun updateTopic(topic: Topic)

    @Delete
    suspend fun deleteTopic(topic: Topic)

    @Query("SELECT * FROM topic ORDER BY topic_id DESC")
    suspend fun getTopics(): List<Topic>

    @Query("SELECT * FROM topic WHERE topic_id = :topicId")
    suspend fun getTopic(topicId: Long): Topic

    /* IDEA */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIdea(idea: Idea): Long

    @Update
    suspend fun updateIdea(idea: Idea)

    @Delete
    suspend fun deleteIdea(idea: Idea)

    @Query("SELECT * FROM idea ORDER BY idea_id DESC")
    suspend fun getIdeas(): List<Idea>

    @Query("SELECT * FROM idea WHERE idea_id = :ideaId")
    suspend fun getIdea(ideaId: Long): Idea

    /* TOPIC & IDEA */
    suspend fun insertIdeaAndUpdateItsTopic(idea: Idea): Long {
        updateTopic(getTopic(idea.ideaTopicId).apply { topicNumIdeas++ })
        return insertIdea(idea)
    }
}

@Dao
interface DaoSituation {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDay(day: Day): Long

    @Update
    suspend fun updateDay(day: Day)

    @Delete
    suspend fun deleteDay(day: Day)

    @Query("SELECT * FROM day ORDER BY day_id DESC")
    suspend fun getDays(): List<Day>

    @Query("SELECT * FROM day WHERE day_id = :dayId")
    suspend fun getDay(dayId: Long): Day
}