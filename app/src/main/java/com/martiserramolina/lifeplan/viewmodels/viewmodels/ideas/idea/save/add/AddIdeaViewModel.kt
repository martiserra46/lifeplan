package com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.idea.save.add

import android.app.Application
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.enums.SaveOperation
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.idea.save.SaveIdeaViewModel

class AddIdeaViewModel(
    topic: Topic,
    application: Application
) : SaveIdeaViewModel(Idea(), topic, application, SaveOperation.ADD)