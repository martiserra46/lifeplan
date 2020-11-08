package com.martiserramolina.lifeplan.ui.fragments.instructions.sections.notes

import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.ui.fragments.instructions.sections.SectionInstructionsFragment

class NotesInstructionsFragment : SectionInstructionsFragment() {

    override fun getTitleText(): String =
        requireContext().getString(R.string.notes_instructions_title)
    override fun getDescriptionText(): String =
        requireContext().getString(R.string.notes_instructions_description)

    override fun navigateToNextFragment() {
        mainActivity.navController
            .navigate(NotesInstructionsFragmentDirections
                .actionNotesInstructionsFragmentToStatusInstructionsFragment())
    }

    override fun navigateToPreviousFragment() {
        mainActivity.navController.navigateUp()
    }

}