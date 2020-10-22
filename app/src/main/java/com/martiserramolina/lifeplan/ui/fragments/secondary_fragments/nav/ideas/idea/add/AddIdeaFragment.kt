package com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.nav.ideas.idea.add

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasIdeaSaveBinding
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment
import com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.SecondaryFragment
import com.martiserramolina.lifeplan.viewmodels.ideas.idea.add.AddIdeaViewModel

class AddIdeaFragment : SecondaryFragment<FragmentNavIdeasIdeaSaveBinding>() {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            AddIdeaViewModel.Factory(
                AddIdeaFragmentArgs.fromBundle(requireArguments()).topic,
                requireActivity().application)
        ).get(AddIdeaViewModel::class.java)
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavIdeasIdeaSaveBinding {
        return FragmentNavIdeasIdeaSaveBinding.inflate(inflater, container, false)
    }

    override fun getToolbar(): Toolbar = binding.fragmentNavIdeasIdeaSaveTb

    override fun getToolbarTitle(): String = getString(R.string.add_idea)

    override fun navigateToPreviousFragment() {
        navController.navigate(
            AddIdeaFragmentDirections.actionAddIdeaFragmentToTopicFragment(viewModel.topic)
        )
    }
}