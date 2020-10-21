package com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.nav.ideas.topic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasTopicBinding
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.SecondaryFragment
import com.martiserramolina.lifeplan.viewmodels.ideas.topic.TopicViewModel

class TopicFragment : SecondaryFragment<FragmentNavIdeasTopicBinding>() {

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
    }

    private fun setupTitleTv() {
        binding.fragmentNavIdeasTopicTitleTv.text = viewModel.topic.topicText
    }
}