package com.martiserramolina.lifeplan.repository

import com.martiserramolina.lifeplan.repository.room.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LifeRepository(private val daoLife: DaoLife) {

    suspend fun getLife(): Life? {
        return withContext(Dispatchers.IO) { daoLife.getLife() }
    }

    suspend fun insertLife(life: Life): Long {
        return withContext(Dispatchers.IO) { daoLife.insertLife(life) }
    }
}

class IdeasRepository(private val daoIdeas: DaoIdeas) {

    suspend fun getTopics(): List<Topic> {
        return withContext(Dispatchers.IO) { daoIdeas.getTopics() }
    }

    suspend fun insertTopic(topic: Topic) {
        return withContext(Dispatchers.IO) { daoIdeas.insertTopic(topic) }
    }

    suspend fun updateTopic(topic: Topic) {
        withContext(Dispatchers.IO) { daoIdeas.updateTopic(topic) }
    }

    suspend fun getIdeas(): List<Idea> {
        return withContext(Dispatchers.IO) { daoIdeas.getIdeas() }
    }

    suspend fun insertIdea(idea: Idea) {
        return withContext(Dispatchers.IO) { daoIdeas.insertIdeaAndUpdateItsTopic(idea) }
    }

    suspend fun updateIdea(idea: Idea) {
        withContext(Dispatchers.IO) { daoIdeas.updateIdea(idea) }
    }
}

class SituationRepository(private val daoSituation: DaoSituation) {

    suspend fun getDays(): List<Day> {
        return withContext(Dispatchers.IO) { daoSituation.getDays() }
    }

    suspend fun insertDay(day: Day): Long {
        return withContext(Dispatchers.IO) { daoSituation.insertDay(day) }
    }

    suspend fun updateDay(day: Day) {
        withContext(Dispatchers.IO) { daoSituation.updateDay(day) }
    }
}