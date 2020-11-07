package com.martiserramolina.lifeplan.ui.fragments.sections.up.notes.notebook.info

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavNotesNotebookBinding
import com.martiserramolina.lifeplan.utils.enums.NavSection
import com.martiserramolina.lifeplan.repository.room.Note
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.notes.note.NoteAdapter
import com.martiserramolina.lifeplan.ui.dialogs.DeleteItemDialogFragment
import com.martiserramolina.lifeplan.utils.functions.showMessage
import com.martiserramolina.lifeplan.ui.fragments.sections.up.notes.notebook.UpNotebookFragment
import com.martiserramolina.lifeplan.utils.interfaces.LoadItemsFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.notebook.info.InfoNotebookViewModel

class UpInfoNotebookFragment :
    UpNotebookFragment<FragmentNavNotesNotebookBinding>(),
    LoadItemsFragment {

    private val viewModel by ViewModelFactory.Delegate(
        this, InfoNotebookViewModel::class.java
    ) {
        val args = UpInfoNotebookFragmentArgs.fromBundle(requireArguments())
        InfoNotebookViewModel(args.notebook, mainActivity.application)
    }

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavNotesNotebookBinding = FragmentNavNotesNotebookBinding.inflate(
        inflater, container, false
    )

    override fun getToolbar(): Toolbar = binding.fragmentNavNotesNotebookTb

    override fun navigateToPreviousFragment() {
        mainActivity.navController.navigate(
            UpInfoNotebookFragmentDirections.actionNotebookFragmentToMainFragment(NavSection.NOTES)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupWhenNotebookDeletedFunctionality()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.notes_notebook_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.notes_notebook_add_note_mi -> onAddMenuItemSelected { navigateToAddNoteFragment() }
            R.id.notes_notebook_edit_mi -> onEditMenuItemSelected()
            R.id.notes_notebook_delete_mi -> onDeleteMenuItemSelected()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupViews() {
        setupTitleTextView()
        setupItemsRecyclerView(
            binding.fragmentNavNotesNotebookRv,
            NoteAdapter { navigateToNoteFragment(it) },
            viewModel,
            viewLifecycleOwner,
            binding.fragmentNavNotesNotebookEmptyCl
        )
    }

    private fun setupWhenNotebookDeletedFunctionality() {
        viewModel.itemDeleted.observe(viewLifecycleOwner) { notebookDeleted ->
            if (notebookDeleted){
                navigateToPreviousFragment()
                showMessage(binding.root, R.string.notebook_deleted)
            }
        }
    }

    private fun onEditMenuItemSelected(): Boolean = navigateToEditNotebookFragment().run { true }

    private fun onDeleteMenuItemSelected(): Boolean = deleteNotebook().run { true }

    private fun setupTitleTextView() {
        binding.fragmentNavNotesNotebookTitleTv.text = viewModel.notebook.notebookText
    }

    private fun navigateToNoteFragment(note: Note) {
        mainActivity.navController.navigate(
            UpInfoNotebookFragmentDirections.actionNotebookFragmentToNoteFragment(note, viewModel.notebook)
        )
    }

    private fun navigateToAddNoteFragment() {
        mainActivity.navController
            .navigate(UpInfoNotebookFragmentDirections.actionNotebookFragmentToAddNoteFragment(viewModel.notebook))
    }

    private fun navigateToEditNotebookFragment() {
        mainActivity.navController.navigate(
            UpInfoNotebookFragmentDirections.actionNotebookFragmentToEditNotebookFragment(viewModel.notebook)
        )
    }

    private fun deleteNotebook() {
        DeleteItemDialogFragment(
            R.string.dialog_message_delete_notebook,
            { viewModel.deleteItem() }
        ).show(parentFragmentManager, getString(R.string.dialog_message_delete_notebook))
    }
}