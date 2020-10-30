package com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.ideas.topic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.RviIdeasTopicBinding
import com.martiserramolina.lifeplan.repository.room.Topic

class TopicAdapter(
    private val onTopicClick: (Topic) -> Unit
) : RecyclerView.Adapter<TopicAdapter.ViewHolder>() {

    var listTopics = emptyList<Topic>()
        set(value) {
            val oldValue = field
            field = value.toList()
            DiffUtil.calculateDiff(TopicListDiffCallback(oldValue, field))
                .dispatchUpdatesTo(this)
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
                rviIdeasTopicTitleTv.text = topic.topicText
                rviIdeasTopicNumIdeasTv.text = root.context
                    .getString(R.string.num_ideas, topic.topicNumIdeas)
                root.setOnClickListener { onTopicClick(topic) }
            }
        }
    }

    class TopicListDiffCallback(
        private val oldTopicList: List<Topic>, private val newTopicList: List<Topic>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldTopicList.size
        override fun getNewListSize() = newTopicList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldTopicList[oldItemPosition].topicId == newTopicList[newItemPosition].topicId
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldTopicList[oldItemPosition] == newTopicList[newItemPosition]
    }
}