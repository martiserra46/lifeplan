package com.martiserramolina.lifeplan.ui.fragments.sections.up.notes.notebook.save.edit

import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.ui.fragments.sections.up.notes.notebook.save.UpSaveNotebookFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.notebook.save.edit.EditNotebookViewModel

class UpEditNotebookFragment : UpSaveNotebookFragment() {

    override val viewModel by ViewModelFactory.Delegate(
        this, EditNotebookViewModel::class.java
    ) {
        val args = UpEditNotebookFragmentArgs.fromBundle(requireArguments())
        EditNotebookViewModel(args.notebook, mainActivity.application)
    }

    override fun getMenuResource(): Int = R.menu.notes_notebook_edit_menu

    override fun getSaveMenuItemId(): Int = R.id.notes_notebook_edit_save_mi

    override fun navigateToPreviousFragment() {
        mainActivity.navController.navigate(
            UpEditNotebookFragmentDirections.actionEditNotebookFragmentToNotebookFragment(viewModel.notebook)
        )
    }

    override fun getNotebookSavedMessage(): Int = R.string.notebook_edited
}