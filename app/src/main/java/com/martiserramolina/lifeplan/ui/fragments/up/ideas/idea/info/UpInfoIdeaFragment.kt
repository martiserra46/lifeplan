package com.martiserramolina.lifeplan.ui.fragments.up.ideas.idea.info

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasIdeaBinding
import com.martiserramolina.lifeplan.dialogs.DeleteItemDialogFragment
import com.martiserramolina.lifeplan.functions.showMessageWithDelay
import com.martiserramolina.lifeplan.ui.fragments.up.ideas.UpIdeasFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.idea.info.InfoIdeaViewModel

class UpInfoIdeaFragment : UpIdeasFragment<FragmentNavIdeasIdeaBinding>() {

    private val viewModel by ViewModelFactory.Delegate(
        this, InfoIdeaViewModel::class.java
    ) {
        val args = UpInfoIdeaFragmentArgs.fromBundle(requireArguments())
        InfoIdeaViewModel(args.idea, args.topic, mainActivity.application)
    }

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavIdeasIdeaBinding = FragmentNavIdeasIdeaBinding.inflate(
        inflater, container, false
    )

    override fun getToolbar(): Toolbar = binding.fragmentNavIdeasIdeaTb

    override fun navigateToPreviousFragment() {
        mainActivity.navController
            .navigate(UpInfoIdeaFragmentDirections.actionIdeaFragmentToTopicFragment(viewModel.topic))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupWhenIdeaDeletedFunctionality()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.ideas_idea_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.ideas_idea_edit_mi -> onEditMenuItemSelected()
            R.id.ideas_idea_delete_mi -> onDeleteMenuItemSelected()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupViews() {
        setupTitleTextView()
        setupImportanceTextView()
        setupDescriptionTextView()
    }

    private fun setupWhenIdeaDeletedFunctionality() {
        viewModel.ideaDeleted.observe(viewLifecycleOwner) { ideaDeleted ->
            if (ideaDeleted) {
                navigateToPreviousFragment()
                showMessageWithDelay(binding.root, R.string.idea_deleted)
            }
        }
    }

    private fun onEditMenuItemSelected(): Boolean = navigateToEditIdeaFragment().run { true }

    private fun onDeleteMenuItemSelected(): Boolean = deleteIdea().run { true }

    private fun setupTitleTextView() {
        binding.fragmentNavIdeasIdeaTitleTv.text = viewModel.idea.ideaTitle
    }

    private fun setupImportanceTextView() {
        viewModel.idea.ideaImportance.let { importance ->
            binding.fragmentNavIdeasIdeaImportanceTv.apply {
                text = getString(importance.stringId)
                setTextColor(ContextCompat.getColor(context, importance.colorId))
            }
        }
    }

    private fun setupDescriptionTextView() {
        binding.fragmentNavIdeasIdeaDescriptionTv.text = viewModel.idea.ideaDescription
    }

    private fun navigateToEditIdeaFragment() {
        mainActivity.navController.navigate(
            UpInfoIdeaFragmentDirections.actionIdeaFragmentToEditIdeaFragment(
                viewModel.idea,
                viewModel.topic
            )
        )
    }

    private fun deleteIdea() {
        DeleteItemDialogFragment(
            R.string.dialog_message_delete_item,
            { viewModel.deleteIdea() }
        ).show(parentFragmentManager, getString(R.string.dialog_message_delete_item))
    }
}