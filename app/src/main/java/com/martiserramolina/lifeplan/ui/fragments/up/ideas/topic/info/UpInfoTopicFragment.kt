package com.martiserramolina.lifeplan.ui.fragments.up.ideas.topic.info

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasTopicBinding
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.ideas.idea.IdeaAdapter
import com.martiserramolina.lifeplan.ui.fragments.up.ideas.topic.UpTopicFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.topic.info.InfoTopicViewModel

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
        whenTopicDeletedNavigateToPreviousFragment()
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

    private fun whenTopicDeletedNavigateToPreviousFragment() {
        viewModel.topicDeleted.observe(viewLifecycleOwner) { topicDeleted ->
            if (topicDeleted) navigateToPreviousFragment()
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
            setHasFixedSize(true)
            adapter = IdeaAdapter { navigateToIdeaFragment(it) }
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                    setDrawable(ContextCompat.getDrawable(context, R.drawable.div_rvi)!!)
                }
            )
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    onScrollIdeasRv()
                }
            })
        }
        viewModel.itemsFetched.observe(viewLifecycleOwner) { ideas ->
            binding.fragmentNavIdeasTopicRv.adapter.run { this as IdeaAdapter }.items = ideas
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
        viewModel.deleteTopic()
    }

    private fun navigateToIdeaFragment(idea: Idea) {
        mainActivity.navController.navigate(
            UpInfoTopicFragmentDirections.actionTopicFragmentToIdeaFragment(idea, viewModel.topic)
        )
    }

    private fun onScrollIdeasRv() {
        val lastVisibleIdeaPosition = binding.fragmentNavIdeasTopicRv.layoutManager
            .run { this as LinearLayoutManager }.findLastVisibleItemPosition()
        val lastIdeaPositionRv = binding.fragmentNavIdeasTopicRv.adapter
            .run { this as IdeaAdapter }.items.size - 1
        if (lastVisibleIdeaPosition == lastIdeaPositionRv) {
            viewModel.fetchItemsIfNotFetched((lastIdeaPositionRv + 1).toLong())
        }
    }
}