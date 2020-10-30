package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.topic

import android.app.Application
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.IdeasViewModel

abstract class TopicViewModel(
    val topic: Topic,
    application: Application
) : IdeasViewModel(application)