package com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.nav.ideas.idea.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasIdeaSaveBinding
import com.martiserramolina.lifeplan.ui.adapters.DaySatisfactionAdapter
import com.martiserramolina.lifeplan.ui.adapters.IdeaImportanceAdapter
import com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.SecondaryFragment
import com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.nav.ideas.idea.IdeaFragmentArgs
import com.martiserramolina.lifeplan.viewmodels.ideas.idea.edit.EditIdeaViewModel

class EditIdeaFragment : SecondaryFragment<FragmentNavIdeasIdeaSaveBinding>() {

    private val viewModel by lazy {
        val args = IdeaFragmentArgs.fromBundle(requireArguments())
        ViewModelProvider(
            this,
            EditIdeaViewModel.Factory(args.topic, args.idea, requireActivity().application)
        ).get(EditIdeaViewModel::class.java)
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavIdeasIdeaSaveBinding {
        return FragmentNavIdeasIdeaSaveBinding.inflate(inflater, container, false)
    }

    override fun getToolbar(): Toolbar = binding.fragmentNavIdeasIdeaSaveTb

    override fun getToolbarTitle(): String = ""

    override fun navigateToPreviousFragment() {
        navController.navigate(
            EditIdeaFragmentDirections
                .actionEditIdeaFragmentToIdeaFragment(viewModel.idea, viewModel.topic)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTitleTv()
        setupImportanceSp()
        setupDescriptionTv()
    }

    private fun setupTitleTv() {
        binding.fragmentNavIdeasIdeaSaveTitleEt.setText(viewModel.idea.ideaTitle)
    }

    private fun setupImportanceSp() {
        binding.fragmentNavIdeasIdeaSaveImportanceSp.apply {
            adapter = IdeaImportanceAdapter(
                requireContext(), R.layout.spinner_item
            ).apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
            setSelection(viewModel.idea.ideaImportance.ordinal)
        }
    }

    private fun setupDescriptionTv() {
        binding.fragmentNavIdeasIdeaSaveDescriptionEt.setText(viewModel.idea.ideaDescription)
    }
}