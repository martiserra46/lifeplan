package com.martiserramolina.lifeplan.ui.fragments.nav.ideas

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasBinding
import com.martiserramolina.lifeplan.ui.activities.MainActivity
import com.martiserramolina.lifeplan.ui.adapters.TopicAdapter
import com.martiserramolina.lifeplan.ui.fragments.FragmentWithBinding
import com.martiserramolina.lifeplan.ui.fragments.MainFragmentDirections
import com.martiserramolina.lifeplan.ui.fragments.nav.ideas.idea.IdeaFragmentDirections
import com.martiserramolina.lifeplan.ui.fragments.nav.ideas.topic.TopicFragmentDirections
import com.martiserramolina.lifeplan.viewmodels.ideas.IdeasViewModel

class IdeasFragment : FragmentWithBinding<FragmentNavIdeasBinding>() {

    private val mainActivity by lazy { activity as MainActivity }

    private val viewModel by lazy {
        ViewModelProvider(
            this, IdeasViewModel.Factory(requireActivity().application)
        ).get(IdeasViewModel::class.java)
    }

    override fun getBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentNavIdeasBinding {
        return FragmentNavIdeasBinding.inflate(inflater, container, false)
    }

    override fun getRootView(): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.ideas_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.ideas_topic_add_mi -> {
                navigateToAddTopicFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupRecyclerView() {
        binding.fragmentNavIdeasRv.apply {
            setHasFixedSize(true)
            adapter = TopicAdapter()
        }
        viewModel.topics.observe(viewLifecycleOwner) { topics ->
            binding.fragmentNavIdeasRv.adapter.run { this as TopicAdapter }.listTopics = topics
        }
    }

    private fun navigateToAddTopicFragment() {
        mainActivity.navController
            .navigate(MainFragmentDirections.actionMainFragmentToAddTopicFragment())
    }
}