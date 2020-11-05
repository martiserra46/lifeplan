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

    suspend fun deleteLife() {
        withContext(Dispatchers.IO) { daoLife.deleteLife() }
    }
}

class NotesRepository(private val daoNotes: DaoNotes): Repository() {

    suspend fun getNotebooks(position: Long, numNotebooks: Int): List<Notebook> {
        return withContext(Dispatchers.IO) { daoNotes.getNotebooks(position, numNotebooks) }
    }

    suspend fun insertNotebook(notebook: Notebook): Long {
        return withContext(Dispatchers.IO) { daoNotes.insertNotebook(notebook) }
    }

    suspend fun updateNotebook(notebook: Notebook) {
        withContext(Dispatchers.IO) { daoNotes.updateNotebook(notebook) }
    }

    suspend fun deleteNotebook(notebook: Notebook) {
        withContext(Dispatchers.IO) { daoNotes.deleteNotebook(notebook) }
    }

    suspend fun getNotes(notebookId: Long, position: Long, numNotes: Int): List<Note> {
        return withContext(Dispatchers.IO) { daoNotes.getNotes(notebookId, position, numNotes) }
    }

    suspend fun insertNote(note: Note): Long {
        return withContext(Dispatchers.IO) { daoNotes.insertNoteAndUpdateItsNotebook(note) }
    }

    suspend fun updateNote(note: Note) {
        withContext(Dispatchers.IO) { daoNotes.updateNote(note) }
    }

    suspend fun deleteNote(note: Note) {
        withContext(Dispatchers.IO) { daoNotes.deleteNoteAndUpdateItsNotebook(note) }
    }
}

class StatusRepository(private val daoStatus: DaoStatus): Repository() {

    suspend fun getDays(position: Long, numDays: Int): List<Day> {
        return withContext(Dispatchers.IO) { daoStatus.getDays(position, numDays) }
    }

    suspend fun insertDay(day: Day): Long {
        return withContext(Dispatchers.IO) { daoStatus.insertDay(day) }
    }

    suspend fun updateDay(day: Day) {
        withContext(Dispatchers.IO) { daoStatus.updateDay(day) }
    }

    suspend fun deleteDay(day: Day) {
        withContext(Dispatchers.IO) { daoStatus.deleteDay(day) }
    }
}