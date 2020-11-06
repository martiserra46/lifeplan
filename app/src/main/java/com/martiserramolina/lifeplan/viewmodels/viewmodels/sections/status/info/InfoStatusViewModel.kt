package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.status.info

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.PagedList
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.viewmodels.interfaces.LoadItemsViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.status.StatusViewModel

class InfoStatusViewModel(
    application: Application
) : StatusViewModel(application), LoadItemsViewModel<Day> {
    override val items: LiveData<PagedList<Day>> = repository.getDays()
}