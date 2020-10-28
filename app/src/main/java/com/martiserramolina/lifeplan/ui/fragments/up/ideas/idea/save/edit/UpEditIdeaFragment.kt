package com.martiserramolina.lifeplan.ui.fragments.up.ideas.idea.save.edit

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasIdeaSaveBinding
import com.martiserramolina.lifeplan.repository.enums.IdeaImportance
import com.martiserramolina.lifeplan.ui.adapters.IdeaImportanceAdapter
import com.martiserramolina.lifeplan.ui.fragments.up.ideas.idea.save.UpSaveIdeaFragment
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.idea.save.edit.EditIdeaViewModel
import java.util.*

class UpEditIdeaFragment : UpSaveIdeaFragment() {

    private val viewModel by lazy {
        val args = EditIdeaFragmentArgs.fromBundle(requireArguments())
        ViewModelProvider(
            this,
            EditIdeaViewModel.Factory(args.topic, args.idea, requireActivity().application)
        ).get(EditIdeaViewModel::class.java)
    }

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavIdeasIdeaSaveBinding {
        return FragmentNavIdeasIdeaSaveBinding.inflate(inflater, container, false)
    }

    override fun getToolbar(): Toolbar = binding.fragmentNavIdeasIdeaSaveTb

    override fun getToolbarTitle(): String = ""

    override fun navigateToPreviousFragment() {
        mainActivity.navController.navigate(
            EditIdeaFragmentDirections.actionEditIdeaFragmentToIdeaFragment(
                viewModel.idea,
                viewModel.topic
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTitleTv()
        setupImportanceSp()
        setupDescriptionTv()
        navigateToPreviousFragmentAfterDbOp()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.ideas_idea_edit_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.ideas_idea_edit_save_mi -> {
                saveIdea()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupTitleTv() {
        binding.fragmentNavIdeasIdeaSaveTitleEt.setText(viewModel.idea.ideaTitle)
    }

    private fun setupImportanceSp() {
        binding.fragmentNavIdeasIdeaSaveImportanceSp.apply {
            adapter = IdeaImportanceAdapter(
                requireContext(), R.layout.spinner_item
            ).apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
            setSelection(viewModel.idea.ideaImportance.ordinal)
        }
    }

    private fun setupDescriptionTv() {
        binding.fragmentNavIdeasIdeaSaveDescriptionEt.setText(viewModel.idea.ideaDescription)
    }

    private fun saveIdea() {
        viewModel.idea.apply {
            ideaTopicId = getTopicId()
            ideaTitle = getTitle()
            ideaImportance = getImportance()
            ideaDescription = getDescription()
            ideaLastTimeModified = getDate()
        }
        viewModel.editIdea()
    }

    private fun getTopicId(): Long {
        return viewModel.topic.topicId
    }

    private fun getTitle(): String {
        return binding.fragmentNavIdeasIdeaSaveTitleEt.text.toString()
    }

    private fun getImportance(): IdeaImportance {
        return binding.fragmentNavIdeasIdeaSaveImportanceSp.selectedItem
            .run { this as IdeaImportance }
    }

    private fun getDescription(): String {
        return binding.fragmentNavIdeasIdeaSaveDescriptionEt.text.toString()
    }

    private fun getDate(): Date {
        return Date()
    }

    private fun navigateToPreviousFragmentAfterDbOp() {
        viewModel.ideaEdited.observe(viewLifecycleOwner) { ideaEdited ->
            if (ideaEdited) navigateToPreviousFragment()
        }
    }
}