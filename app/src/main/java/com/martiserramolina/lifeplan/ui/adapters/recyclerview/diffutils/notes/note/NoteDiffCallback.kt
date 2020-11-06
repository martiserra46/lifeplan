package com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.notes.note

import com.martiserramolina.lifeplan.repository.room.Note
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.ItemDiffCallback

class NoteDiffCallback : ItemDiffCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean =
        oldItem.noteId == newItem.noteId
    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean =
        oldItem == newItem
}