package com.martiserramolina.lifeplan.ui.fragments.instructions.sections

import android.os.Bundle
import android.view.*
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentInstructionsSectionBinding
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment

abstract class SectionInstructionsFragment : BaseFragment<FragmentInstructionsSectionBinding>() {

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
            fragmentInstructionsSectionTitleTv.text = getTitleText()
            fragmentInstructionsSectionDescriptionTv.text = getDescriptionText()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.section_instructions_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.section_instructions_next_mi -> navigateToNextFragment().run { true }
            R.id.section_instructions_previous_mi -> navigateToPreviousFragment().run { true }
            else -> super.onOptionsItemSelected(item)
        }
    }

    abstract fun getTitleText(): String
    abstract fun getDescriptionText(): String

    abstract fun navigateToNextFragment()
    abstract fun navigateToPreviousFragment()
}