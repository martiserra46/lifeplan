package com.martiserramolina.lifeplan.ui.fragments.main_fragments.nav

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasBinding
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.ui.activities.MainActivity
import com.martiserramolina.lifeplan.ui.adapters.TopicAdapter
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment
import com.martiserramolina.lifeplan.ui.fragments.main_fragments.MainFragmentDirections
import com.martiserramolina.lifeplan.viewmodels.ideas.IdeasViewModel

class IdeasFragment : BaseFragment<FragmentNavIdeasBinding>() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupTopicsRv()
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

    private fun setupTopicsRv() {
        binding.fragmentNavIdeasRv.apply {
            setHasFixedSize(true)
            adapter = TopicAdapter { navigateToTopicFragment(it) }
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                    setDrawable(ContextCompat.getDrawable(context, R.drawable.div_rvi)!!)
                }
            )
        }
        viewModel.topics.observe(viewLifecycleOwner) { topics ->
            binding.fragmentNavIdeasRv.adapter.run { this as TopicAdapter }.listTopics = topics
        }
    }

    private fun navigateToAddTopicFragment() {
        mainActivity.navController
            .navigate(MainFragmentDirections.actionMainFragmentToAddTopicFragment())
    }

    private fun navigateToTopicFragment(topic: Topic) {
        mainActivity.navController
            .navigate(MainFragmentDirections.actionMainFragmentToTopicFragment(topic))
    }
}