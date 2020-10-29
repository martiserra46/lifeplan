package com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.ideas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.martiserramolina.lifeplan.databinding.RviIdeasIdeaBinding
import com.martiserramolina.lifeplan.extensions.formatted
import com.martiserramolina.lifeplan.repository.room.Idea

class IdeaAdapter(
    private val onIdeaClick: (Idea) -> Unit
) : RecyclerView.Adapter<IdeaAdapter.ViewHolder>() {

    var listIdeas = emptyList<Idea>()
        set(value) {
            val oldValue = field
            field = value.toList()
            DiffUtil.calculateDiff(IdeaListDiffCallback(oldValue, field))
                .dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent, onIdeaClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listIdeas[position])
    }

    override fun getItemCount(): Int {
        return listIdeas.size
    }

    class ViewHolder(
        private val binding: RviIdeasIdeaBinding,
        private val onIdeaClick: (Idea) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup, onIdeaClick: (Idea) -> Unit): ViewHolder {
                return ViewHolder(
                    RviIdeasIdeaBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    ), onIdeaClick
                )
            }
        }

        fun bind(idea: Idea) {
            binding.apply {
                rviIdeasIdeaDateTv.text = idea.ideaLastTimeModified.formatted()
                rviIdeasIdeaTitleTv.text = idea.ideaTitle
                rviIdeasIdeaImportanceV.setBackgroundResource(idea.ideaImportance.drawableId)
                root.setOnClickListener { onIdeaClick(idea) }
            }
        }
    }

    class IdeaListDiffCallback(
        private val oldIdeaList: List<Idea>, private val newIdeaList: List<Idea>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldIdeaList.size
        override fun getNewListSize() = newIdeaList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldIdeaList[oldItemPosition].ideaId == newIdeaList[newItemPosition].ideaId
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldIdeaList[oldItemPosition] == newIdeaList[newItemPosition]
    }
}