package com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.ideas.idea

import android.view.LayoutInflater
import android.view.ViewGroup
import com.martiserramolina.lifeplan.databinding.RviIdeasIdeaBinding
import com.martiserramolina.lifeplan.extensions.formatted
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.ItemViewHolder

class IdeaViewHolder(
    parent: ViewGroup,
    onItemClick: (Idea) -> Unit
) : ItemViewHolder<RviIdeasIdeaBinding, Idea>(
    RviIdeasIdeaBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
    ), onItemClick
) {
    override fun bindData(item: Idea) {
        binding.apply {
            rviIdeasIdeaDateTv.text = item.ideaLastTimeModified.formatted()
            rviIdeasIdeaTitleTv.text = item.ideaTitle
            rviIdeasIdeaImportanceV.setBackgroundResource(item.ideaImportance.drawableId)
        }
    }
}