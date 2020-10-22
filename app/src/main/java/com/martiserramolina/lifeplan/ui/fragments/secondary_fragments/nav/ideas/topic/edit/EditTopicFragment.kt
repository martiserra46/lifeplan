package com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.nav.ideas.topic.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasTopicSaveBinding
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment
import com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.SecondaryFragment
import com.martiserramolina.lifeplan.viewmodels.ideas.topic.edit.EditTopicViewModel

class EditTopicFragment : SecondaryFragment<FragmentNavIdeasTopicSaveBinding>() {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            EditTopicViewModel.Factory(
                EditTopicFragmentArgs.fromBundle(requireArguments()).topic,
                requireActivity().application
            )
        ).get(EditTopicViewModel::class.java)
    }
    
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavIdeasTopicSaveBinding {
        return FragmentNavIdeasTopicSaveBinding.inflate(inflater, container, false)
    }

    override fun getToolbar(): Toolbar = binding.fragmentNavIdeasTopicSaveTb

    override fun getToolbarTitle(): String = getString(R.string.edit)

    override fun navigateToPreviousFragment() {
        navController
            .navigate(EditTopicFragmentDirections.actionEditTopicFragmentToTopicFragment(viewModel.topic))
    }
}