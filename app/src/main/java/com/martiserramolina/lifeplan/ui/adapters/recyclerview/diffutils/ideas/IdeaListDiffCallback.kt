package com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.ideas

import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.ItemListDiffCallback

class IdeaListDiffCallback(
    oldList: List<Idea>,
    newList: List<Idea>
) : ItemListDiffCallback<Idea>(oldList, newList) {
    override fun getItemId(item: Idea): Long = item.ideaId
}