package com.martiserramolina.lifeplan.repository

import com.martiserramolina.lifeplan.repository.room.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

sealed class Repository

class LifeRepository(private val daoLife: DaoLife): Repository() {

    suspend fun getLife(): Life {
        return withContext(Dispatchers.IO) { daoLife.getLife() ?: Life() }
    }

    suspend fun insertLife(life: Life): Long {
        return withContext(Dispatchers.IO) { daoLife.insertLife(life) }
    }
}

class IdeasRepository(private val daoIdeas: DaoIdeas): Repository() {

    suspend fun getTopics(position: Long, numTopics: Int): List<Topic> {
        return withContext(Dispatchers.IO) { daoIdeas.getTopics(position, numTopics) }
    }

    suspend fun insertTopic(topic: Topic): Long {
        return withContext(Dispatchers.IO) { daoIdeas.insertTopic(topic) }
    }

    suspend fun updateTopic(topic: Topic) {
        withContext(Dispatchers.IO) { daoIdeas.updateTopic(topic) }
    }

    suspend fun deleteTopic(topic: Topic) {
        withContext(Dispatchers.IO) { daoIdeas.deleteTopic(topic) }
    }

    suspend fun getIdeas(topicId: Long, position: Long, numIdeas: Int): List<Idea> {
        return withContext(Dispatchers.IO) { daoIdeas.getIdeas(topicId, position, numIdeas) }
    }

    suspend fun insertIdea(idea: Idea): Long {
        return withContext(Dispatchers.IO) { daoIdeas.insertIdeaAndUpdateItsTopic(idea) }
    }

    suspend fun updateIdea(idea: Idea) {
        withContext(Dispatchers.IO) { daoIdeas.updateIdea(idea) }
    }

    suspend fun deleteIdea(idea: Idea) {
        withContext(Dispatchers.IO) { daoIdeas.deleteIdeaAndUpdateItsTopic(idea) }
    }
}

class SituationRepository(private val daoSituation: DaoSituation): Repository() {

    suspend fun getDays(position: Long, numDays: Int): List<Day> {
        return withContext(Dispatchers.IO) { daoSituation.getDays(position, numDays) }
    }

    suspend fun insertDay(day: Day): Long {
        return withContext(Dispatchers.IO) { daoSituation.insertDay(day) }
    }

    suspend fun updateDay(day: Day) {
        withContext(Dispatchers.IO) { daoSituation.updateDay(day) }
    }

    suspend fun deleteDay(day: Day) {
        withContext(Dispatchers.IO) { daoSituation.deleteDay(day) }
    }
}