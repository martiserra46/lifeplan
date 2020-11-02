package com.martiserramolina.lifeplan.adapters.recyclerview.viewholders.ideas.topic

import android.view.LayoutInflater
import android.view.ViewGroup
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.RviIdeasTopicBinding
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.adapters.recyclerview.viewholders.ItemViewHolder

class TopicViewHolder(
    parent: ViewGroup,
    onItemClick: (Topic) -> Unit
) : ItemViewHolder<RviIdeasTopicBinding, Topic>(
    RviIdeasTopicBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
    ), onItemClick
) {
    override fun bindData(item: Topic) {
        binding.apply {
            rviIdeasTopicTitleTv.text = item.topicText
            rviIdeasTopicNumIdeasTv.text = root.context.resources
                .getQuantityString(R.plurals.num_notes, item.topicNumIdeas, item.topicNumIdeas)
        }
    }
}