package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.life.save

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Life
import com.martiserramolina.lifeplan.viewmodels.interfaces.SaveItemViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.life.LifeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SaveLifeViewModel(
    val life: Life,
    application: Application
) : LifeViewModel(application), SaveItemViewModel {
    private val saveItemViewModel = object : SaveItemViewModel.Object() {
        override val coroutineScope get() = viewModelScope
        override suspend fun saveItemToDatabase() {
            repository.insertLife(life)
        }
    }

    override val itemSaved get() = saveItemViewModel.itemSaved
    override fun saveItem() = saveItemViewModel.saveItem()
}