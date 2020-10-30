package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.topic.save.edit

import android.app.Application
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.topic.save.SaveTopicViewModel

class EditTopicViewModel(
    topic: Topic,
    application: Application
) : SaveTopicViewModel(topic, application) {
    override suspend fun saveTopicToDatabase() {
        repository.updateTopic(topic)
    }
}