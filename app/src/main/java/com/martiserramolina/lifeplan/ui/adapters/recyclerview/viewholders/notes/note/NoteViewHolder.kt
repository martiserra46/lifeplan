package com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.notes.note

import android.view.LayoutInflater
import android.view.ViewGroup
import com.martiserramolina.lifeplan.databinding.RviNotesNoteBinding
import com.martiserramolina.lifeplan.utils.functions.dayString
import com.martiserramolina.lifeplan.repository.room.Note
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.ItemViewHolder

class NoteViewHolder(
    parent: ViewGroup,
    onItemClick: (Note) -> Unit
) : ItemViewHolder<RviNotesNoteBinding, Note>(
    RviNotesNoteBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
    ), onItemClick
) {
    override fun bindData(item: Note) {
        binding.apply {
            rviNotesNoteDayTv.text = item.noteLastTimeModified.dayString()
            rviNotesNoteTitleTv.text = item.noteTitle
            rviNotesNoteImportanceV.setBackgroundResource(item.noteImportance.drawableId)
        }
    }
}