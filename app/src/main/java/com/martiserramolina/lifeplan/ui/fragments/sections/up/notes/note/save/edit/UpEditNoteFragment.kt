package com.martiserramolina.lifeplan.ui.fragments.sections.up.notes.note.save.edit

import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.ui.fragments.sections.up.notes.note.save.UpSaveNoteFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.note.save.edit.EditNoteViewModel

class UpEditNoteFragment : UpSaveNoteFragment() {

    override val viewModel by ViewModelFactory.Delegate(
        this, EditNoteViewModel::class.java
    ) {
        val args = UpEditNoteFragmentArgs.fromBundle(requireArguments())
        EditNoteViewModel(args.note, args.notebook, mainActivity.application)
    }

    override fun getMenuResource(): Int = R.menu.notes_note_edit_menu

    override fun getSaveMenuItemId(): Int = R.id.notes_note_edit_save_mi

    override fun navigateToPreviousFragment() {
        mainActivity.navController.navigate(
            UpEditNoteFragmentDirections
                .actionEditNoteFragmentToNoteFragment(viewModel.note, viewModel.notebook)
        )
    }

    override fun getNoteSavedMessage(): Int = R.string.note_edited
}