package com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.notes.notebook

import android.view.ViewGroup
import com.martiserramolina.lifeplan.repository.room.Notebook
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.ItemAdapter
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.notes.notebook.NotebookListDiffCallback
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.notes.notebook.NotebookViewHolder

class NotebookAdapter(
    onItemClick: (Notebook) -> Unit
) : ItemAdapter<NotebookViewHolder, NotebookListDiffCallback, Notebook>(onItemClick) {

    override fun buildViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotebookViewHolder = NotebookViewHolder(parent, onItemClick)

    override fun buildItemListDiffCallback(
        oldList: List<Notebook>,
        newList: List<Notebook>
    ): NotebookListDiffCallback = NotebookListDiffCallback(oldList, newList)
}