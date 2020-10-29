package com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.topic.save.add

import android.app.Application
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.topic.save.SaveTopicViewModel

class AddTopicViewModel(
    application: Application
) : SaveTopicViewModel(Topic(), application) {
    override suspend fun saveTopicToDatabase() {
        repository.insertTopic(topic)
    }
}