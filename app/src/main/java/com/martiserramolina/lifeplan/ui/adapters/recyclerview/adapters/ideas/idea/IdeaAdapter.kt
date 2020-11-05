package com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.ideas.idea

import android.view.ViewGroup
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.ItemAdapter
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.ideas.idea.IdeaListDiffCallback
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.ideas.idea.IdeaViewHolder

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