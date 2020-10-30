package com.martiserramolina.lifeplan.adapters.recyclerview.adapters.ideas.idea

import android.view.ViewGroup
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.adapters.recyclerview.adapters.ItemAdapter
import com.martiserramolina.lifeplan.adapters.recyclerview.diffutils.ideas.idea.IdeaListDiffCallback
import com.martiserramolina.lifeplan.adapters.recyclerview.viewholders.ideas.idea.IdeaViewHolder

class IdeaAdapter(
    onItemClick: (Idea) -> Unit
) : ItemAdapter<IdeaViewHolder, IdeaListDiffCallback, Idea>(onItemClick) {

    override fun buildViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IdeaViewHolder = IdeaViewHolder(parent, onItemClick)

    override fun buildItemListDiffCallback(
        oldList: List<Idea>,
        newList: List<Idea>
    ): IdeaListDiffCallback = IdeaListDiffCallback(oldList, newList)
}