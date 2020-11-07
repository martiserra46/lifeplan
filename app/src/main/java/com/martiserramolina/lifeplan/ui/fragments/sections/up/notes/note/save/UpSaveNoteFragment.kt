package com.martiserramolina.lifeplan.ui.fragments.sections.up.notes.note.save

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavNotesNoteSaveBinding
import com.martiserramolina.lifeplan.repository.enums.NoteImportance
import com.martiserramolina.lifeplan.ui.adapters.spinner.note_importance.NoteImportanceAdapter
import com.martiserramolina.lifeplan.utils.functions.showMessage
import com.martiserramolina.lifeplan.ui.fragments.sections.up.notes.note.UpNoteFragment
import com.martiserramolina.lifeplan.utils.interfaces.SaveItemFragment
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.note.save.SaveNoteViewModel
import java.util.*

abstract class UpSaveNoteFragment :
    UpNoteFragment<FragmentNavNotesNoteSaveBinding>(),
    SaveItemFragment {

    protected abstract val viewModel: SaveNoteViewModel

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavNotesNoteSaveBinding = FragmentNavNotesNoteSaveBinding.inflate(
        inflater, container, false
    )

    override fun getToolbar(): Toolbar = binding.fragmentNavNotesNoteSaveTb

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews {
            setupTitleEditText()
            setupImportanceSpinner()
            setupDescriptionEditText()
        }
        setupWhenItemSavedFunctionality(viewModel, viewLifecycleOwner) {
            navigateToPreviousFragment()
            showMessage(binding.root, getNoteSavedMessage())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(getMenuResource(), menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            getSaveMenuItemId() -> onSaveMenuItemSelected(
                ::isNoteValid, ::saveNote, ::showMessageInvalidNote)
            else -> super.onOptionsItemSelected(item)
        }
    }

    abstract fun getMenuResource(): Int

    abstract fun getSaveMenuItemId(): Int

    private fun setupTitleEditText() {
        binding.fragmentNavNotesNoteSaveTitleEt.setText(viewModel.note.noteTitle)
    }

    private fun setupImportanceSpinner() {
        binding.fragmentNavNotesNoteSaveImportanceSp.apply {
            adapter = NoteImportanceAdapter(requireContext())
            setSelection(viewModel.note.noteImportance.ordinal)
        }
    }

    private fun setupDescriptionEditText() {
        binding.fragmentNavNotesNoteSaveDescriptionEt.setText(viewModel.note.noteDescription)
    }

    private fun isNoteValid(): Boolean = getTitleFromEditText().isNotEmpty() &&
            getDescriptionFromEditText().isNotEmpty()

    private fun saveNote() {
        viewModel.note.apply {
            noteNotebookId = viewModel.notebook.notebookId
            noteTitle = getTitleFromEditText()
            noteImportance = getImportanceFromSpinner()
            noteDescription = getDescriptionFromEditText()
            noteLastTimeModified = Date()
        }
        viewModel.saveItem()
    }

    private fun showMessageInvalidNote() {
        showMessage(binding.root, R.string.invalid_note)
    }

    protected abstract fun getNoteSavedMessage(): Int

    private fun getTitleFromEditText(): String {
        return binding.fragmentNavNotesNoteSaveTitleEt.text.toString()
    }

    private fun getImportanceFromSpinner(): NoteImportance {
        return binding.fragmentNavNotesNoteSaveImportanceSp.selectedItem
            .run { this as NoteImportance }
    }

    private fun getDescriptionFromEditText(): String {
        return binding.fragmentNavNotesNoteSaveDescriptionEt.text.toString()
    }
}