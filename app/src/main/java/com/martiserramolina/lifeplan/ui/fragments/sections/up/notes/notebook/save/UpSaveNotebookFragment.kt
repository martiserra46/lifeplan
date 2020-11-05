package com.martiserramolina.lifeplan.ui.fragments.sections.up.notes.notebook.save

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavNotesNotebookSaveBinding
import com.martiserramolina.lifeplan.utils.functions.showMessage
import com.martiserramolina.lifeplan.ui.fragments.sections.up.notes.notebook.UpNotebookFragment
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.notebook.save.SaveNotebookViewModel

abstract class UpSaveNotebookFragment : UpNotebookFragment<FragmentNavNotesNotebookSaveBinding>() {

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
        setupViews()
        setupWhenNotebookSavedFunctionality()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(getMenuResource(), menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            getSaveMenuItemId() -> onSaveMenuItemSelected()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupViews() {
        setupTitleTextView()
    }

    private fun setupWhenNotebookSavedFunctionality() {
        viewModel.itemSaved.observe(viewLifecycleOwner) { notebookSaved ->
            if (notebookSaved) {
                navigateToPreviousFragment()
                showMessage(binding.root, getNotebookSavedMessage())
            }
        }
    }

    protected abstract fun getMenuResource(): Int

    protected abstract fun getSaveMenuItemId(): Int

    private fun onSaveMenuItemSelected(): Boolean = saveNotebookIfValid().run { true }

    private fun setupTitleTextView() {
        binding.fragmentNavNotesNotebookSaveTitleEt.setText(viewModel.notebook.notebookText)
    }

    private fun saveNotebookIfValid() {
        if (isNotebookValid()) saveNotebook() else showMessage(binding.root, R.string.invalid_notebook)
    }

    private fun isNotebookValid(): Boolean = getTitleFromEditText().isNotEmpty()

    private fun saveNotebook() {
        viewModel.notebook.apply {
            notebookText = getTitleFromEditText()
        }
        viewModel.saveItem()
    }

    protected abstract fun getNotebookSavedMessage(): Int

    private fun getTitleFromEditText(): String {
        return binding.fragmentNavNotesNotebookSaveTitleEt.text.toString()
    }
}