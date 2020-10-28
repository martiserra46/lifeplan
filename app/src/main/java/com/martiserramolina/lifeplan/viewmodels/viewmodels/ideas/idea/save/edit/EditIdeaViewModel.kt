package com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.idea.save.edit

import android.app.Application
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.enums.SaveOperation
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.idea.save.SaveIdeaViewModel

class EditIdeaViewModel(
    idea: Idea,
    topic: Topic,
    application: Application
) : SaveIdeaViewModel(idea, topic, application, SaveOperation.EDIT)