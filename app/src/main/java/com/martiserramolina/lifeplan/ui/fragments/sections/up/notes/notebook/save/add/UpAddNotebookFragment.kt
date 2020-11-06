package com.martiserramolina.lifeplan.ui.fragments.sections.up.notes.notebook.save.add

import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.utils.enums.NavSection
import com.martiserramolina.lifeplan.ui.fragments.sections.up.notes.notebook.save.UpSaveNotebookFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.notebook.save.add.AddNotebookViewModel

class UpAddNotebookFragment : UpSaveNotebookFragment() {

    override val viewModel by ViewModelFactory.Delegate(
        this, AddNotebookViewModel::class.java
    ) { AddNotebookViewModel(mainActivity.application) }

    override fun getToolbarTitle(): String = getString(R.string.notebook_add_message)

    override fun getMenuResource(): Int = R.menu.notes_notebook_add_menu

    override fun getSaveMenuItemId(): Int = R.id.notes_notebook_add_save_mi

    override fun navigateToPreviousFragment() {
        mainActivity.navController.navigate(
            UpAddNotebookFragmentDirections.actionAddNotebookFragmentToMainFragment(NavSection.NOTES)
        )
    }

    override fun getNotebookSavedMessage(): Int = R.string.notebook_added
}