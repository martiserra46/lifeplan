package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.idea

import android.app.Application
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.IdeasViewModel

abstract class IdeaViewModel(
    val idea: Idea,
    val topic: Topic,
    application: Application
): IdeasViewModel(application)