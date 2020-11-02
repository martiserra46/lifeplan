package com.martiserramolina.lifeplan.ui.fragments.nav.ideas

import android.os.Bundle
import android.view.*
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasBinding
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.adapters.recyclerview.adapters.ideas.topic.TopicAdapter
import com.martiserramolina.lifeplan.extensions.setupAutoLoadItemsFunctionality
import com.martiserramolina.lifeplan.ui.fragments.main.MainFragmentDirections
import com.martiserramolina.lifeplan.ui.fragments.nav.NavFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.info.InfoIdeasViewModel

class NavIdeasFragment : NavFragment<FragmentNavIdeasBinding>() {

    private val viewModel by ViewModelFactory.Delegate(
        this, InfoIdeasViewModel::class.java
    ) { InfoIdeasViewModel(mainActivity.application) }

    override fun buildBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentNavIdeasBinding = FragmentNavIdeasBinding.inflate(
        inflater, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupTopicsRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.ideas_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.ideas_topic_add_mi -> onAddMenuItemSelected()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupTopicsRecyclerView() {
        binding.fragmentNavIdeasRv.setupAutoLoadItemsFunctionality(
            viewLifecycleOwner, TopicAdapter { navigateToTopicFragment(it) }, viewModel
        )
    }

    private fun onAddMenuItemSelected(): Boolean = navigateToAddTopicFragment().run { true }

    private fun navigateToAddTopicFragment() {
        mainActivity.navController
            .navigate(MainFragmentDirections.actionMainFragmentToAddTopicFragment())
    }

    private fun navigateToTopicFragment(topic: Topic) {
        mainActivity.navController
            .navigate(MainFragmentDirections.actionMainFragmentToTopicFragment(topic))
    }
}