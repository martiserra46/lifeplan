package com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.idea

import android.app.Application
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.IdeasViewModel

abstract class IdeaViewModel(application: Application): IdeasViewModel(application) {
    protected abstract val idea: Idea
    protected abstract val topic: Topic
}