package com.martiserramolina.lifeplan.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
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

    fun getNotebooks(): LiveData<PagedList<Notebook>> {
        return LivePagedListBuilder(
            daoNotes.getNotebooks(),
            PagedList.Config.Builder()
                .setPageSize(30)
                .setEnablePlaceholders(false)
                .build()
        ).build()
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

    fun getNotes(notebookId: Long): LiveData<PagedList<Note>> {
        return LivePagedListBuilder(
            daoNotes.getNotes(notebookId),
            PagedList.Config.Builder()
                .setPageSize(30)
                .setEnablePlaceholders(false)
                .build()
        ).build()
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

    fun getDays(): LiveData<PagedList<Day>> {
        return LivePagedListBuilder(
            daoStatus.getDays(),
            PagedList.Config.Builder()
                .setPageSize(30)
                .setEnablePlaceholders(false)
                .build()
        ).build()
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