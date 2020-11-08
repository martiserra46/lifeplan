package com.martiserramolina.lifeplan.ui.fragments.instructions.sections.life

import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.ui.fragments.instructions.sections.SectionInstructionsFragment

class LifeInstructionsFragment : SectionInstructionsFragment() {

    override fun getTitleText(): String =
        requireContext().getString(R.string.life_instructions_title)
    override fun getDescriptionText(): String =
        requireContext().getString(R.string.life_instructions_description)

    override fun navigateToNextFragment() {

    }

    override fun navigateToPreviousFragment() {

    }
}