package com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.notes.notebook

import com.martiserramolina.lifeplan.repository.room.Notebook
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.ItemListDiffCallback

class NotebookListDiffCallback(
    oldList: List<Notebook>,
    newList: List<Notebook>
) : ItemListDiffCallback<Notebook>(oldList, newList) {
    override fun getItemId(item: Notebook): Long = item.notebookId
}