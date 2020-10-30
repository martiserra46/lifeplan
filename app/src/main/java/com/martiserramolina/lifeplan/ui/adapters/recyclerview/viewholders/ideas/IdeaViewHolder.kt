package com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.ideas

import com.martiserramolina.lifeplan.databinding.RviIdeasIdeaBinding
import com.martiserramolina.lifeplan.extensions.formatted
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.ItemViewHolder

class IdeaViewHolder(
    binding: RviIdeasIdeaBinding,
    onItemClick: (Idea) -> Unit
) : ItemViewHolder<RviIdeasIdeaBinding, Idea>(binding, onItemClick) {
    override fun bindData(item: Idea) {
        binding.apply {
            rviIdeasIdeaDateTv.text = item.ideaLastTimeModified.formatted()
            rviIdeasIdeaTitleTv.text = item.ideaTitle
            rviIdeasIdeaImportanceV.setBackgroundResource(item.ideaImportance.drawableId)
        }
    }
}