package com.martiserramolina.lifeplan.adapters.recyclerview.diffutils.ideas.idea

import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.adapters.recyclerview.diffutils.ItemListDiffCallback

class IdeaListDiffCallback(
    oldList: List<Idea>,
    newList: List<Idea>
) : ItemListDiffCallback<Idea>(oldList, newList) {
    override fun getItemId(item: Idea): Long = item.ideaId
}