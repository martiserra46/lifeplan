package com.martiserramolina.lifeplan.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.RviIdeasTopicBinding
import com.martiserramolina.lifeplan.repository.model.Topic

class TopicAdapter(
    private val onTopicClick: (Topic) -> Unit
) : RecyclerView.Adapter<TopicAdapter.ViewHolder>() {

    var listTopics = emptyList<Topic>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent, onTopicClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listTopics[position])
    }

    override fun getItemCount(): Int {
        return listTopics.size
    }

    class ViewHolder(
        private val binding: RviIdeasTopicBinding,
        private val onTopicClick: (Topic) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup, onTopicClick: (Topic) -> Unit): ViewHolder {
                return ViewHolder(
                    RviIdeasTopicBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    ), onTopicClick
                )
            }
        }

        fun bind(topic: Topic) {
            binding.apply {
                rviIdeasTopicTitleTv.text = topic.name
                rviIdeasTopicNumIdeasTv.text = root.context
                    .getString(R.string.num_ideas, topic.ideas.size)
                root.setOnClickListener { onTopicClick(topic) }
            }
        }
    }
}