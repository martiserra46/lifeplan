package com.martiserramolina.lifeplan.ui.fragments.instructions.sections

import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentInstructionsSectionBinding
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment
import com.martiserramolina.lifeplan.ui.fragments.instructions.MainInstructionsFragmentArgs
import com.martiserramolina.lifeplan.ui.fragments.instructions.sections.life.LifeInstructionsFragmentArgs

abstract class SectionInstructionsFragment : BaseFragment<FragmentInstructionsSectionBinding>() {

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInstructionsSectionBinding = FragmentInstructionsSectionBinding.inflate(
        inflater, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupViews()
        setupBackButton()
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

    private fun setupToolbar() {
        mainActivity.apply {
            setSupportActionBar(binding.fragmentInstructionsSectionTb)
            supportActionBar?.title = ""
        }
        setHasOptionsMenu(true)
    }

    private fun setupViews() {
        binding.apply {
            fragmentInstructionsSectionTitleTv.text = getTitleText()
            fragmentInstructionsSectionDescriptionTv.text = getDescriptionText()
        }
    }

    private fun setupBackButton() {
        mainActivity.onBackPressedDispatcher
            .addCallback(mainActivity) {
                navigateToPreviousFragment()
            }
    }

    abstract fun getTitleText(): String
    abstract fun getDescriptionText(): String

    abstract fun navigateToNextFragment()
    abstract fun navigateToPreviousFragment()
}