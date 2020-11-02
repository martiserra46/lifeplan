package com.martiserramolina.lifeplan.ui.fragments.up.ideas.topic.info

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasTopicBinding
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.adapters.recyclerview.adapters.ideas.idea.IdeaAdapter
import com.martiserramolina.lifeplan.dialogs.DeleteItemDialogFragment
import com.martiserramolina.lifeplan.extensions.setupAutoLoadItemsFunctionality
import com.martiserramolina.lifeplan.functions.showMessageWithDelay
import com.martiserramolina.lifeplan.ui.fragments.up.ideas.topic.UpTopicFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.topic.info.InfoTopicViewModel

class UpInfoTopicFragment : UpTopicFragment<FragmentNavIdeasTopicBinding>() {

    private val viewModel by ViewModelFactory.Delegate(
        this, InfoTopicViewModel::class.java
    ) {
        val args = UpInfoTopicFragmentArgs.fromBundle(requireArguments())
        InfoTopicViewModel(args.topic, mainActivity.application)
    }

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavIdeasTopicBinding = FragmentNavIdeasTopicBinding.inflate(
        inflater, container, false
    )

    override fun getToolbar(): Toolbar = binding.fragmentNavIdeasTopicTb

    override fun navigateToPreviousFragment() {
        mainActivity.navController.navigate(
            UpInfoTopicFragmentDirections.actionTopicFragmentToMainFragment(NavSection.IDEAS)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupWhenTopicDeletedFunctionality()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.ideas_topic_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.ideas_topic_add_idea_mi -> onAddMenuItemSelected()
            R.id.ideas_topic_edit_mi -> onEditMenuItemSelected()
            R.id.ideas_topic_delete_mi -> onDeleteMenuItemSelected()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupViews() {
        setupTitleTextView()
        setupIdeasRecyclerView()
    }

    private fun setupWhenTopicDeletedFunctionality() {
        viewModel.topicDeleted.observe(viewLifecycleOwner) { topicDeleted ->
            if (topicDeleted){
                navigateToPreviousFragment()
                showMessageWithDelay(binding.root, R.string.notebook_deleted)
            }
        }
    }

    private fun onAddMenuItemSelected(): Boolean = navigateToAddIdeaFragment().run { true }

    private fun onEditMenuItemSelected(): Boolean = navigateToEditTopicFragment().run { true }

    private fun onDeleteMenuItemSelected(): Boolean = deleteTopic().run { true }

    private fun setupTitleTextView() {
        binding.fragmentNavIdeasTopicTitleTv.text = viewModel.topic.topicText
    }

    private fun setupIdeasRecyclerView() {
        binding.fragmentNavIdeasTopicRv.apply {
            setupAutoLoadItemsFunctionality(
                viewLifecycleOwner, IdeaAdapter { navigateToIdeaFragment(it) }, viewModel
            )
        }
    }

    private fun navigateToAddIdeaFragment() {
        mainActivity.navController
            .navigate(UpInfoTopicFragmentDirections.actionTopicFragmentToAddIdeaFragment(viewModel.topic))
    }

    private fun navigateToEditTopicFragment() {
        mainActivity.navController.navigate(
            UpInfoTopicFragmentDirections.actionTopicFragmentToEditTopicFragment(viewModel.topic)
        )
    }

    private fun deleteTopic() {
        DeleteItemDialogFragment(
            R.string.dialog_message_delete_notebook,
            { viewModel.deleteTopic() }
        ).show(parentFragmentManager, getString(R.string.dialog_message_delete_notebook))
    }

    private fun navigateToIdeaFragment(idea: Idea) {
        mainActivity.navController.navigate(
            UpInfoTopicFragmentDirections.actionTopicFragmentToIdeaFragment(idea, viewModel.topic)
        )
    }
}