package com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.notes.note

import android.view.ViewGroup
import com.martiserramolina.lifeplan.repository.room.Note
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.ItemAdapter
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.notes.note.NoteListDiffCallback
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.notes.note.NoteViewHolder

class NoteAdapter(
    onItemClick: (Note) -> Unit
) : ItemAdapter<NoteViewHolder, NoteListDiffCallback, Note>(onItemClick) {

    override fun buildViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteViewHolder = NoteViewHolder(parent, onItemClick)

    override fun buildItemListDiffCallback(
        oldList: List<Note>,
        newList: List<Note>
    ): NoteListDiffCallback = NoteListDiffCallback(oldList, newList)
}