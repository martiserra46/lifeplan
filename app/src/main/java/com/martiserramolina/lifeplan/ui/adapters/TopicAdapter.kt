package com.martiserramolina.lifeplan.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.RviIdeasTopicBinding

class TopicAdapter : RecyclerView.Adapter<TopicAdapter.ViewHolder>() {

    private val listTopics = listOf(
        TopicItem("hola", 12),
        TopicItem("adeu", 2)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listTopics[position])
    }

    override fun getItemCount(): Int {
        return listTopics.size
    }

    data class TopicItem(val title: String, val numIdeas: Int)

    class ViewHolder(
        private val binding: RviIdeasTopicBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                return ViewHolder(
                    RviIdeasTopicBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }
        }

        fun bind(topicItem: TopicItem) {
            binding.apply {
                rviIdeasTopicTitleTv.text = topicItem.title
                rviIdeasTopicNumIdeasTv.text = root.context
                    .getString(R.string.num_ideas, topicItem.numIdeas)
            }
        }
    }
}