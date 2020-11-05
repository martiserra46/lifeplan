package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.topic.save

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.viewmodels.interfaces.SaveItemViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.topic.TopicViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class SaveTopicViewModel(
    topic: Topic,
    application: Application
) : TopicViewModel(topic, application), SaveItemViewModel {
    private val saveItemViewModel = object : SaveItemViewModel.Object() {
        override val coroutineScope = viewModelScope
        override suspend fun saveItemToDatabase() {
            this@SaveTopicViewModel.saveItemToDatabase()
        }
    }
    override val itemSaved = saveItemViewModel.itemSaved
    override fun saveItem() = saveItemViewModel.saveItem()

    abstract suspend fun saveItemToDatabase()
}