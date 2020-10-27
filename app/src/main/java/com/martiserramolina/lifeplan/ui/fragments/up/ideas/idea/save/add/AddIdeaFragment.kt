package com.martiserramolina.lifeplan.ui.fragments.up.ideas.idea.save.add

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasIdeaSaveBinding
import com.martiserramolina.lifeplan.repository.enums.IdeaImportance
import com.martiserramolina.lifeplan.ui.adapters.IdeaImportanceAdapter
import com.martiserramolina.lifeplan.ui.fragments.up.UpButtonFragment
import com.martiserramolina.lifeplan.viewmodels.nav.ideas.idea.save.add.AddIdeaViewModel
import java.util.*

class AddIdeaFragment : UpButtonFragment<FragmentNavIdeasIdeaSaveBinding>() {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            AddIdeaViewModel.Factory(
                AddIdeaFragmentArgs.fromBundle(requireArguments()).topic,
                requireActivity().application)
        ).get(AddIdeaViewModel::class.java)
    }

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavIdeasIdeaSaveBinding {
        return FragmentNavIdeasIdeaSaveBinding.inflate(inflater, container, false)
    }

    override fun getToolbar(): Toolbar = binding.fragmentNavIdeasIdeaSaveTb

    override fun getToolbarTitle(): String = getString(R.string.add_idea)

    override fun navigateToPreviousFragment() {
        navController.navigate(
            AddIdeaFragmentDirections.actionAddIdeaFragmentToTopicFragment(viewModel.topic)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupImportanceSp()
        navigateToPreviousFragmentAfterDbOp()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.ideas_idea_add_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.ideas_idea_add_save_mi -> {
                saveIdea()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupImportanceSp() {
        binding.fragmentNavIdeasIdeaSaveImportanceSp.apply {
            adapter = IdeaImportanceAdapter(
                requireContext(), R.layout.spinner_item
            ).apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
        }
    }

    private fun saveIdea() {
        viewModel.idea.apply {
            ideaTopicId = getTopicId()
            ideaTitle = getTitle()
            ideaImportance = getImportance()
            ideaDescription = getDescription()
            ideaLastTimeModified = getDate()
        }
        viewModel.addIdea()
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
        viewModel.ideaAdded.observe(viewLifecycleOwner) { ideaInserted ->
            if (ideaInserted) navigateToPreviousFragment()
        }
    }
}