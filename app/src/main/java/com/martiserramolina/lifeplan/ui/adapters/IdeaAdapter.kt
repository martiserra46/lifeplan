package com.martiserramolina.lifeplan.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.martiserramolina.lifeplan.databinding.RviIdeasIdeaBinding
import com.martiserramolina.lifeplan.extensions.format
import com.martiserramolina.lifeplan.repository.room.Idea

class IdeaAdapter(
    private val onIdeaClick: (Idea) -> Unit
) : RecyclerView.Adapter<IdeaAdapter.ViewHolder>() {

    var listIdeas = emptyList<Idea>()
        set(value) {
            field = value
            notifyDataSetChanged()
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
                rviIdeasIdeaDateTv.text = idea.ideaLastTimeModified.format("dd/mm/yyyy")
                rviIdeasIdeaTitleTv.text = idea.ideaText
                rviIdeasIdeaImportanceV.setBackgroundResource(idea.ideaImportance.drawableId)
                root.setOnClickListener { onIdeaClick(idea) }
            }
        }
    }
}