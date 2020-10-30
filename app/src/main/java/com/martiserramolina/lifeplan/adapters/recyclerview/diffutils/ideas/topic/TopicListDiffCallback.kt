package com.martiserramolina.lifeplan.adapters.recyclerview.diffutils.ideas.topic

import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.adapters.recyclerview.diffutils.ItemListDiffCallback

class TopicListDiffCallback(
    oldList: List<Topic>,
    newList: List<Topic>
) : ItemListDiffCallback<Topic>(oldList, newList) {
    override fun getItemId(item: Topic): Long = item.topicId
}