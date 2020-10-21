package com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.nav.ideas.topic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasTopicBinding
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.SecondaryFragment

class TopicFragment : SecondaryFragment<FragmentNavIdeasTopicBinding>() {

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
}