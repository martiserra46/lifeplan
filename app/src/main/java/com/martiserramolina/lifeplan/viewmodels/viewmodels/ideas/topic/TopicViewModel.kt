package com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.topic

import android.app.Application
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.IdeasViewModel

abstract class TopicViewModel(
    protected val topic: Topic,
    application: Application
) : IdeasViewModel(application)