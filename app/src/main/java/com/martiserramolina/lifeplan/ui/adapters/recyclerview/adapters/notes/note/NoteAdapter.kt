package com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.notes.note

import android.view.ViewGroup
import com.martiserramolina.lifeplan.repository.room.Note
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.ItemAdapter
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.notes.note.NoteDiffCallback
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.notes.note.NoteViewHolder

class NoteAdapter(
    private val onItemClick: (Note) -> Unit
) : ItemAdapter<Note, NoteViewHolder, NoteDiffCallback>(NoteDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder(parent, onItemClick)
}