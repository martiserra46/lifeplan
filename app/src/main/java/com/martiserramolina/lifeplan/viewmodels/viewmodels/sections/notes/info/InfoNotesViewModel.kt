package com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.info

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.PagedList
import com.martiserramolina.lifeplan.repository.room.Note
import com.martiserramolina.lifeplan.repository.room.Notebook
import com.martiserramolina.lifeplan.viewmodels.interfaces.LoadItemsViewModel
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.NotesViewModel

class InfoNotesViewModel(
    application: Application
) : NotesViewModel(application), LoadItemsViewModel<Notebook> {
    override val items: LiveData<PagedList<Notebook>> = repository.getNotebooks()
}