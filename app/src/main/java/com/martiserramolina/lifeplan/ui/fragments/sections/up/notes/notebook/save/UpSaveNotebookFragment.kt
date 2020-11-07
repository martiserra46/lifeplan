package com.martiserramolina.lifeplan.ui.fragments.sections.up.notes.notebook.save

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavNotesNotebookSaveBinding
import com.martiserramolina.lifeplan.utils.functions.showMessage
import com.martiserramolina.lifeplan.ui.fragments.sections.up.notes.notebook.UpNotebookFragment
import com.martiserramolina.lifeplan.utils.interfaces.SaveItemFragment
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.notebook.save.SaveNotebookViewModel

abstract class UpSaveNotebookFragment :
    UpNotebookFragment<FragmentNavNotesNotebookSaveBinding>(),
    SaveItemFragment {

    protected abstract val viewModel: SaveNotebookViewModel

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavNotesNotebookSaveBinding = FragmentNavNotesNotebookSaveBinding.inflate(
        inflater, container, false
    )

    override fun getToolbar(): Toolbar = binding.fragmentNavNotesNotebookSaveTb

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews { setupTitleTextView() }
        setupWhenItemSavedFunctionality(viewModel, viewLifecycleOwner) {
            navigateToPreviousFragment()
            showMessage(binding.root, getNotebookSavedMessage())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(getMenuResource(), menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            getSaveMenuItemId() -> onSaveMenuItemSelected(
                ::isNotebookValid, ::saveNotebook, ::showMessageInvalidNotebook)
            else -> super.onOptionsItemSelected(item)
        }
    }

    protected abstract fun getMenuResource(): Int

    protected abstract fun getSaveMenuItemId(): Int

    private fun setupTitleTextView() {
        binding.fragmentNavNotesNotebookSaveTitleEt.setText(viewModel.notebook.notebookText)
    }

    private fun isNotebookValid(): Boolean = getTitleFromEditText().isNotEmpty()

    private fun saveNotebook() {
        viewModel.notebook.apply {
            notebookText = getTitleFromEditText()
        }
        viewModel.saveItem()
    }

    private fun showMessageInvalidNotebook() {
        showMessage(binding.root, R.string.invalid_notebook)
    }

    protected abstract fun getNotebookSavedMessage(): Int

    private fun getTitleFromEditText(): String {
        return binding.fragmentNavNotesNotebookSaveTitleEt.text.toString()
    }
}