package com.martiserramolina.lifeplan.ui.fragments.instructions.sections.life

import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.ui.fragments.instructions.sections.SectionInstructionsFragment

class LifeInstructionsFragment : SectionInstructionsFragment() {

    val isBackToMainFragmentEnabled by lazy {
        LifeInstructionsFragmentArgs.fromBundle(requireArguments()).isBackToMainFragmentEnabled
    }

    override fun getTitleText(): String =
        requireContext().getString(R.string.life_instructions_title)
    override fun getDescriptionText(): String =
        requireContext().getString(R.string.life_instructions_description)

    override fun navigateToNextFragment() {
        mainActivity.navController
            .navigate(LifeInstructionsFragmentDirections
                .actionLifeInstructionsFragmentToNotesInstructionsFragment(isBackToMainFragmentEnabled))
    }

    override fun navigateToPreviousFragment() {
        mainActivity.navController
            .navigate(LifeInstructionsFragmentDirections
                .actionLifeInstructionsFragmentToMainInstructionsFragment(isBackToMainFragmentEnabled))
    }
}