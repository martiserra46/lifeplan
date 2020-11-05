package com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.notes.notebook

import android.view.LayoutInflater
import android.view.ViewGroup
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.RviNotesNotebookBinding
import com.martiserramolina.lifeplan.repository.room.Notebook
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.ItemViewHolder

class NotebookViewHolder(
    parent: ViewGroup,
    onItemClick: (Notebook) -> Unit
) : ItemViewHolder<RviNotesNotebookBinding, Notebook>(
    RviNotesNotebookBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
    ), onItemClick
) {
    override fun bindData(item: Notebook) {
        binding.apply {
            rviNotesNotebookTitleTv.text = item.notebookText
            rviNotesNotebookNumNotesTv.text = root.context.resources
                .getQuantityString(R.plurals.num_notes, item.notebookNumNotes, item.notebookNumNotes)
        }
    }
}