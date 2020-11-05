package com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.ideas.topic

import android.view.ViewGroup
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.ItemAdapter
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.ideas.topic.TopicListDiffCallback
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.ideas.topic.TopicViewHolder

class TopicAdapter(
    onItemClick: (Topic) -> Unit
) : ItemAdapter<TopicViewHolder, TopicListDiffCallback, Topic>(onItemClick) {

    override fun buildViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopicViewHolder = TopicViewHolder(parent, onItemClick)

    override fun buildItemListDiffCallback(
        oldList: List<Topic>,
        newList: List<Topic>
    ): TopicListDiffCallback = TopicListDiffCallback(oldList, newList)
}