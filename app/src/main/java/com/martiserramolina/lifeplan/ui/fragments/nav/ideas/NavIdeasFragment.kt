package com.martiserramolina.lifeplan.ui.fragments.nav.ideas

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasBinding
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.ui.adapters.TopicAdapter
import com.martiserramolina.lifeplan.ui.fragments.MainFragmentDirections
import com.martiserramolina.lifeplan.ui.fragments.nav.NavFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.info.InfoIdeasViewModel

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
        binding.fragmentNavIdeasRv.apply {
            setHasFixedSize(true)
            adapter = TopicAdapter { navigateToTopicFragment(it) }
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                    setDrawable(ContextCompat.getDrawable(context, R.drawable.div_rvi)!!)
                }
            )
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    onScrollTopicsRv()
                }
            })
        }
        viewModel.topics.observe(viewLifecycleOwner) { topics ->
            binding.fragmentNavIdeasRv.adapter.run { this as TopicAdapter }.listTopics = topics
        }
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

    private fun onScrollTopicsRv() {
        val lastVisibleTopicPosition = binding.fragmentNavIdeasRv.layoutManager
            .run { this as LinearLayoutManager }.findLastVisibleItemPosition()
        val lastTopicPositionRv = binding.fragmentNavIdeasRv.adapter
            .run { this as TopicAdapter }.listTopics.size - 1
        if (lastVisibleTopicPosition == lastTopicPositionRv) {
            viewModel.fetchTopicsFromPositionIfNotFetched(lastTopicPositionRv + 1)
        }
    }
}