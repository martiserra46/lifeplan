package com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.nav.ideas.idea

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasIdeaBinding
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment
import com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.SecondaryFragment
import com.martiserramolina.lifeplan.viewmodels.ideas.idea.IdeaViewModel

class IdeaFragment : SecondaryFragment<FragmentNavIdeasIdeaBinding>() {

    private val viewModel by lazy {
        val args = IdeaFragmentArgs.fromBundle(requireArguments())
        ViewModelProvider(
            this,
            IdeaViewModel.Factory(args.topic, args.idea, requireActivity().application)
        ).get(IdeaViewModel::class.java)
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavIdeasIdeaBinding {
        return FragmentNavIdeasIdeaBinding.inflate(inflater, container, false)
    }

    override fun getToolbar(): Toolbar = binding.fragmentNavIdeasIdeaTb

    override fun getToolbarTitle(): String = ""

    override fun navigateToPreviousFragment() {
        navController
            .navigate(IdeaFragmentDirections.actionIdeaFragmentToTopicFragment(viewModel.topic))
    }

}