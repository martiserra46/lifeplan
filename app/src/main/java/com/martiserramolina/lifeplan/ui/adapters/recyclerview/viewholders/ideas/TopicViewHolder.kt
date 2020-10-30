package com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.ideas

import android.view.LayoutInflater
import android.view.ViewGroup
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.RviIdeasTopicBinding
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.ItemViewHolder

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
            rviIdeasTopicNumIdeasTv.text = root.context
                .getString(R.string.num_ideas, item.topicNumIdeas)
        }
    }
}