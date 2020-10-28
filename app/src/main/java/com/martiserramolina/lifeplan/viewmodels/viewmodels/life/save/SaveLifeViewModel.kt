package com.martiserramolina.lifeplan.viewmodels.viewmodels.life.save

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Life
import com.martiserramolina.lifeplan.viewmodels.viewmodels.life.LifeViewModel
import kotlinx.coroutines.launch

class SaveLifeViewModel(
    val life: Life,
    application: Application
) : LifeViewModel(application) {
    val lifeSaved = MutableLiveData<Boolean>().apply { value = false }
    fun saveLife() {
        viewModelScope.launch {
            repository.insertLife(life)
            lifeSaved.value = true
        }
    }
}