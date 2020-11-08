package com.martiserramolina.lifeplan.ui.fragments.instructions.sections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martiserramolina.lifeplan.databinding.FragmentInstructionsSectionBinding
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment
import com.martiserramolina.lifeplan.utils.enums.InstructionsSection
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.instructions.SectionInstructionsViewModel

abstract class SectionInstructionsFragment : BaseFragment<FragmentInstructionsSectionBinding>() {

    private val viewModel by ViewModelFactory.Delegate(
        this, SectionInstructionsViewModel::class.java
    ) { SectionInstructionsViewModel(getInstructionsSection(), mainActivity.application) }

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInstructionsSectionBinding = FragmentInstructionsSectionBinding.inflate(
        inflater, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding.apply {
            fragmentInstructionsSectionTitleTv.text = viewModel.title
            fragmentInstructionsSectionDescriptionTv.text = viewModel.description
        }
    }

    abstract fun getInstructionsSection(): InstructionsSection
}