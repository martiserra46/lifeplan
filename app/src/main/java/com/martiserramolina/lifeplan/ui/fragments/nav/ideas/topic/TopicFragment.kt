package com.martiserramolina.lifeplan.ui.fragments.nav.ideas.topic

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasTopicBinding
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.repository.room.Idea
import com.martiserramolina.lifeplan.ui.adapters.IdeaAdapter
import com.martiserramolina.lifeplan.ui.fragments.abstracts.UpButtonFragment
import com.martiserramolina.lifeplan.viewmodels.ideas.topic.TopicViewModel

class TopicFragment : UpButtonFragment<FragmentNavIdeasTopicBinding>() {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            TopicViewModel.Factory(
                TopicFragmentArgs.fromBundle(requireArguments()).topic,
                requireActivity().application
            )
        ).get(TopicViewModel::class.java)
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavIdeasTopicBinding {
        return FragmentNavIdeasTopicBinding.inflate(inflater, container, false)
    }

    override fun getToolbar(): Toolbar = binding.fragmentNavIdeasTopicTb

    override fun getToolbarTitle(): String = ""

    override fun navigateToPreviousFragment() {
        navController
            .navigate(TopicFragmentDirections.actionTopicFragmentToMainFragment(NavSection.IDEAS))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTitleTv()
        setupIdeasRv()
        navigateToPreviousFragmentAfterDbOp()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.ideas_topic_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.ideas_topic_add_idea_mi -> {
                navigateToAddIdeaFragment()
                true
            }
            R.id.ideas_topic_edit_mi -> {
                navigateToEditTopicFragment()
                true
            }
            R.id.ideas_topic_delete_mi -> {
                deleteTopic()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupTitleTv() {
        binding.fragmentNavIdeasTopicTitleTv.text = viewModel.topic.topicText
    }

    private fun setupIdeasRv() {
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
        viewModel.ideas.observe(viewLifecycleOwner) { ideas ->
            binding.fragmentNavIdeasTopicRv.adapter.run { this as IdeaAdapter }.listIdeas = ideas
        }
    }

    private fun navigateToAddIdeaFragment() {
        navController
            .navigate(TopicFragmentDirections.actionTopicFragmentToAddIdeaFragment(viewModel.topic))
    }

    private fun navigateToEditTopicFragment() {
        navController.navigate(
            TopicFragmentDirections.actionTopicFragmentToEditTopicFragment(viewModel.topic)
        )
    }

    private fun deleteTopic() {
        viewModel.deleteTopic()
    }

    private fun navigateToIdeaFragment(idea: Idea) {
        navController.navigate(
            TopicFragmentDirections.actionTopicFragmentToIdeaFragment(idea, viewModel.topic)
        )
    }

    private fun onScrollIdeasRv() {
        val lastVisibleIdeaPosition = binding.fragmentNavIdeasTopicRv.layoutManager
            .run { this as LinearLayoutManager }.findLastVisibleItemPosition()
        val lastIdeaPositionRv = binding.fragmentNavIdeasTopicRv.adapter
            .run { this as IdeaAdapter }.listIdeas.size - 1
        if (lastVisibleIdeaPosition == lastIdeaPositionRv) {
            viewModel.fetchIdeasFromPositionIfNotFetched(lastIdeaPositionRv + 1)
        }
    }

    private fun navigateToPreviousFragmentAfterDbOp() {
        viewModel.topicDeleted.observe(viewLifecycleOwner) { topicDeleted ->
            if (topicDeleted) navigateToPreviousFragment()
        }
    }
}