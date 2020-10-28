package com.martiserramolina.lifeplan.viewmodels.viewmodels.life.edit

import android.app.Application
import androidx.lifecycle.*
import com.martiserramolina.lifeplan.repository.room.Life
import com.martiserramolina.lifeplan.viewmodels.viewmodels.life.LifeViewModel
import kotlinx.coroutines.launch

class EditLifeViewModel(
    val life: Life,
    application: Application
) : LifeViewModel(application) {
    val lifeEdited = MutableLiveData<Boolean>().apply { value = false }
    fun editLife() {
        viewModelScope.launch {
            repository.insertLife(life)
            lifeEdited.value = true
        }
    }
}