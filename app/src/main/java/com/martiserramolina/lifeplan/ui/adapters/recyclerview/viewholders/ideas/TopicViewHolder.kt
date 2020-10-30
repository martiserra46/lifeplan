package com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.ideas

import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.RviIdeasTopicBinding
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.ItemViewHolder

class TopicViewHolder(
    binding: RviIdeasTopicBinding,
    onItemClick: (Topic) -> Unit
) : ItemViewHolder<RviIdeasTopicBinding, Topic>(binding, onItemClick) {
    override fun bindItemData(item: Topic) {
        binding.apply {
            rviIdeasTopicTitleTv.text = item.topicText
            rviIdeasTopicNumIdeasTv.text = root.context
                .getString(R.string.num_ideas, item.topicNumIdeas)
        }
    }
}