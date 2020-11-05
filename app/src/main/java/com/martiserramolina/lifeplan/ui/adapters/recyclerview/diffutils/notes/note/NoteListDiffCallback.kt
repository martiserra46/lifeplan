package com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.notes.note

import com.martiserramolina.lifeplan.repository.room.Note
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.ItemListDiffCallback

class NoteListDiffCallback(
    oldList: List<Note>,
    newList: List<Note>
) : ItemListDiffCallback<Note>(oldList, newList) {
    override fun getItemId(item: Note): Long = item.noteId
}