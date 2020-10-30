package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.idea.save.add

import android.app.Application
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.idea.save.SaveIdeaViewModel

class AddIdeaViewModel(
    topic: Topic,
    application: Application
) : SaveIdeaViewModel(Idea(), topic, application) {
    override suspend fun saveIdeaToDatabase() {
        repository.insertIdea(idea)
    }
}