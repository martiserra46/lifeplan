package com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.notes.notebook

import android.view.ViewGroup
import com.martiserramolina.lifeplan.repository.room.Notebook
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.ItemAdapter
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.notes.notebook.NotebookDiffCallback
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.notes.notebook.NotebookViewHolder

class NotebookAdapter(
    private val onItemClick: (Notebook) -> Unit
) : ItemAdapter<Notebook, NotebookViewHolder, NotebookDiffCallback>(NotebookDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotebookViewHolder =
        NotebookViewHolder(parent, onItemClick)
}