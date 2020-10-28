package com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.topic.save.edit

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.enums.SaveOperation
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.topic.TopicViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.topic.save.SaveTopicViewModel
import kotlinx.coroutines.launch

class EditTopicViewModel(
    topic: Topic,
    application: Application
) : SaveTopicViewModel(topic, application, SaveOperation.EDIT)