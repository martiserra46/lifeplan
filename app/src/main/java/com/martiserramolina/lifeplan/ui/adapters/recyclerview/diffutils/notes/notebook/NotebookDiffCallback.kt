package com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.notes.notebook

import com.martiserramolina.lifeplan.repository.room.Notebook
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.ItemDiffCallback

class NotebookDiffCallback : ItemDiffCallback<Notebook>() {
    override fun areItemsTheSame(oldItem: Notebook, newItem: Notebook): Boolean =
        oldItem.notebookId == newItem.notebookId
    override fun areContentsTheSame(oldItem: Notebook, newItem: Notebook): Boolean =
        oldItem == newItem
}