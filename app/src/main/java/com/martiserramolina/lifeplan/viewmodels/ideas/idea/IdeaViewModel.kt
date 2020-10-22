package com.martiserramolina.lifeplan.viewmodels.ideas.idea

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.martiserramolina.lifeplan.repository.IdeasRepository
import com.martiserramolina.lifeplan.repository.room.AppDb
import com.martiserramolina.lifeplan.repository.room.Idea
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class IdeaViewModel(val idea: Idea, application: Application) : AndroidViewModel(application) {

    private val repository by lazy {
        IdeasRepository(AppDb.getInstance(application.applicationContext).daoIdeas())
    }

    fun deleteIdea() {
        viewModelScope.launch {
            repository.deleteIdea(idea)
        }
    }

    class Factory(
        private val idea: Idea, private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(IdeaViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return IdeaViewModel(idea, application) as T
            }
            throw IllegalArgumentException("Invalid ViewModel")
        }
    }
}