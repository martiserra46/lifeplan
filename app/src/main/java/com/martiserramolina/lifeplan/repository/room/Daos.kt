package com.martiserramolina.lifeplan.repository.room

import androidx.room.*

@Dao
interface DaoLife {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLife(life: Life): Long

    @Query("SELECT * FROM life LIMIT 1")
    suspend fun getLife(): Life?

    @Query("DELETE FROM life")
    suspend fun deleteLife()
}

@Dao
interface DaoNotes {
    /* NOTEBOOK */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotebook(notebook: Notebook): Long

    @Update
    suspend fun updateNotebook(notebook: Notebook)

    @Delete
    suspend fun deleteNotebook(notebook: Notebook)

    @Query("SELECT * FROM notebook ORDER BY notebook_id DESC LIMIT :position, :numNotebooks")
    suspend fun getNotebooks(position: Long, numNotebooks: Int): List<Notebook>

    @Query("SELECT * FROM notebook WHERE notebook_id = :notebookId")
    suspend fun getNotebook(notebookId: Long): Notebook

    /* NOTE */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note): Long

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note ORDER BY note_id DESC")
    suspend fun getNotes(): List<Note>

    @Query("SELECT * FROM note WHERE note_notebook_id = :notebookId ORDER BY note_id DESC LIMIT :position, :numNotes")
    suspend fun getNotes(notebookId: Long, position: Long, numNotes: Int): List<Note>

    @Query("SELECT * FROM note WHERE note_id = :noteId")
    suspend fun getNote(noteId: Long): Note

    /* NOTEBOOK & NOTE */
    @Transaction
    suspend fun insertNoteAndUpdateItsNotebook(note: Note): Long {
        updateNotebook(getNotebook(note.noteNotebookId).apply { notebookNumNotes++ })
        return insertNote(note)
    }

    @Transaction
    suspend fun deleteNoteAndUpdateItsNotebook(note: Note) {
        updateNotebook(getNotebook(note.noteNotebookId).apply { notebookNumNotes-- })
        deleteNote(note)
    }
}

@Dao
interface DaoStatus {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDay(day: Day): Long

    @Update
    suspend fun updateDay(day: Day)

    @Delete
    suspend fun deleteDay(day: Day)

    @Query("SELECT * FROM day ORDER BY day_id DESC LIMIT :position, :numDays")
    suspend fun getDays(position: Long, numDays: Int): List<Day>

    @Query("SELECT * FROM day WHERE day_id = :dayId")
    suspend fun getDay(dayId: Long): Day
}