package com.martiserramolina.lifeplan.ui.fragments.sections.up.notes.note.save.add

import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.ui.fragments.sections.up.notes.note.save.UpSaveNoteFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.note.save.add.AddNoteViewModel

class UpAddNoteFragment : UpSaveNoteFragment() {

    override val viewModel by ViewModelFactory.Delegate(
        this, AddNoteViewModel::class.java
    ) {
        val args = UpAddNoteFragmentArgs.fromBundle(requireArguments())
        AddNoteViewModel(args.notebook, mainActivity.application)
    }

    override fun getToolbarTitle(): String = getString(R.string.note_add_message)

    override fun getMenuResource(): Int = R.menu.notes_note_add_menu

    override fun getSaveMenuItemId(): Int = R.id.notes_note_add_save_mi

    override fun navigateToPreviousFragment() {
        mainActivity.navController.navigate(
            UpAddNoteFragmentDirections.actionAddNoteFragmentToNotebookFragment(viewModel.notebook)
        )
    }

    override fun getNoteSavedMessage(): Int = R.string.note_added
}