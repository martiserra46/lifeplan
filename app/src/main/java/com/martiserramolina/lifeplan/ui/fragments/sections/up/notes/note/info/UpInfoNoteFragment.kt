package com.martiserramolina.lifeplan.ui.fragments.sections.up.notes.note.info

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavNotesNoteBinding
import com.martiserramolina.lifeplan.ui.dialogs.DeleteItemDialogFragment
import com.martiserramolina.lifeplan.utils.functions.showMessage
import com.martiserramolina.lifeplan.ui.fragments.sections.up.notes.UpNotesFragment
import com.martiserramolina.lifeplan.utils.interfaces.InfoItemFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.note.info.InfoNoteViewModel

class UpInfoNoteFragment : UpNotesFragment<FragmentNavNotesNoteBinding>(), InfoItemFragment {

    private val viewModel by ViewModelFactory.Delegate(
        this, InfoNoteViewModel::class.java
    ) {
        val args = UpInfoNoteFragmentArgs.fromBundle(requireArguments())
        InfoNoteViewModel(args.note, args.notebook, mainActivity.application)
    }

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavNotesNoteBinding = FragmentNavNotesNoteBinding.inflate(
        inflater, container, false
    )

    override fun getToolbar(): Toolbar = binding.fragmentNavNotesNoteTb

    override fun navigateToPreviousFragment() {
        mainActivity.navController
            .navigate(UpInfoNoteFragmentDirections.actionNoteFragmentToNotebookFragment(viewModel.notebook))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews {
            setupTitleTextView()
            setupImportanceTextView()
            setupDescriptionTextView()
        }
        setupWhenItemDeletedFunctionality(viewModel, viewLifecycleOwner) {
            navigateToPreviousFragment()
            showMessage(binding.root, R.string.note_deleted)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.notes_note_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.notes_note_edit_mi -> onEditMenuItemSelected { navigateToEditNoteFragment() }
            R.id.notes_note_delete_mi -> onDeleteMenuItemSelected { deleteNote() }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupTitleTextView() {
        binding.fragmentNavNotesNoteTitleTv.text = viewModel.note.noteTitle
    }

    private fun setupImportanceTextView() {
        viewModel.note.noteImportance.let { importance ->
            binding.fragmentNavNotesNoteImportanceTv.apply {
                text = getString(importance.stringId)
                setTextColor(ContextCompat.getColor(context, importance.colorId))
            }
        }
    }

    private fun setupDescriptionTextView() {
        binding.fragmentNavNotesNoteDescriptionTv.text = viewModel.note.noteDescription
    }

    private fun navigateToEditNoteFragment() {
        mainActivity.navController.navigate(
            UpInfoNoteFragmentDirections.actionNoteFragmentToEditNoteFragment(
                viewModel.note,
                viewModel.notebook
            )
        )
    }

    private fun deleteNote() {
        DeleteItemDialogFragment(
            R.string.dialog_message_delete_note,
            { viewModel.deleteItem() }
        ).show(parentFragmentManager, getString(R.string.dialog_message_delete_note))
    }
}