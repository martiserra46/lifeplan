package com.martiserramolina.lifeplan.ui.fragments.instructions.sections.status

import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.ui.fragments.instructions.sections.SectionInstructionsFragment

class StatusInstructionsFragment : SectionInstructionsFragment() {
    override fun getTitleText(): String =
        requireContext().getString(R.string.status_instructions_title)

    override fun getDescriptionText(): String =
        requireContext().getString(R.string.status_instructions_description)

    override fun navigateToNextFragment() {

    }

    override fun navigateToPreviousFragment() {

    }

}