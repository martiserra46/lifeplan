package com.martiserramolina.lifeplan.ui.fragments.up.ideas.idea.save

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasIdeaSaveBinding
import com.martiserramolina.lifeplan.repository.enums.IdeaImportance
import com.martiserramolina.lifeplan.ui.adapters.spinner.IdeaImportanceAdapter
import com.martiserramolina.lifeplan.ui.fragments.up.ideas.idea.UpIdeaFragment
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.idea.save.SaveIdeaViewModel
import java.util.*

abstract class UpSaveIdeaFragment : UpIdeaFragment<FragmentNavIdeasIdeaSaveBinding>() {

    protected abstract val viewModel: SaveIdeaViewModel

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavIdeasIdeaSaveBinding = FragmentNavIdeasIdeaSaveBinding.inflate(
        inflater, container, false
    )

    override fun getToolbar(): Toolbar = binding.fragmentNavIdeasIdeaSaveTb

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        whenIdeaSavedNavigateToPreviousFragment()
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
        setupTitleEditText()
        setupImportanceSpinner()
        setupDescriptionEditText()
    }

    private fun whenIdeaSavedNavigateToPreviousFragment() {
        viewModel.ideaSaved.observe(viewLifecycleOwner) {
            if (it) navigateToPreviousFragment()
        }
    }

    abstract fun getMenuResource(): Int

    abstract fun getSaveMenuItemId(): Int

    private fun onSaveMenuItemSelected(): Boolean = saveIdeaIfValid().run { true }

    private fun setupTitleEditText() {
        binding.fragmentNavIdeasIdeaSaveTitleEt.setText(viewModel.idea.ideaTitle)
    }

    private fun setupImportanceSpinner() {
        binding.fragmentNavIdeasIdeaSaveImportanceSp.apply {
            adapter = IdeaImportanceAdapter(
                requireContext(), R.layout.spinner_item
            ).apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
            setSelection(viewModel.idea.ideaImportance.ordinal)
        }
    }

    private fun setupDescriptionEditText() {
        binding.fragmentNavIdeasIdeaSaveDescriptionEt.setText(viewModel.idea.ideaDescription)
    }

    private fun saveIdeaIfValid() {
        if (isIdeaValid()) saveIdea() else showInvalidIdeaMessage()
    }

    private fun isIdeaValid(): Boolean = getTitleFromEditText().isNotEmpty() &&
            getDescriptionFromEditText().isNotEmpty()

    private fun saveIdea() {
        viewModel.idea.apply {
            ideaTopicId = viewModel.topic.topicId
            ideaTitle = getTitleFromEditText()
            ideaImportance = getImportanceFromSpinner()
            ideaDescription = getDescriptionFromEditText()
            ideaLastTimeModified = Date()
        }
        viewModel.saveIdea()
    }

    private fun showInvalidIdeaMessage() {
        Toast.makeText(context, getString(R.string.invalid_topic), Toast.LENGTH_SHORT).show()
    }

    private fun getTitleFromEditText(): String {
        return binding.fragmentNavIdeasIdeaSaveTitleEt.text.toString()
    }

    private fun getImportanceFromSpinner(): IdeaImportance {
        return binding.fragmentNavIdeasIdeaSaveImportanceSp.selectedItem
            .run { this as IdeaImportance }
    }

    private fun getDescriptionFromEditText(): String {
        return binding.fragmentNavIdeasIdeaSaveDescriptionEt.text.toString()
    }
}