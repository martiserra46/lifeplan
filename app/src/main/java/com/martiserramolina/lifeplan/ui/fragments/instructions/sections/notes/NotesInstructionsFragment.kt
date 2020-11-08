package com.martiserramolina.lifeplan.ui.fragments.instructions.sections.notes

import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.ui.fragments.instructions.sections.SectionInstructionsFragment

class NotesInstructionsFragment : SectionInstructionsFragment() {

    val isBackToMainFragmentEnabled by lazy {
        NotesInstructionsFragmentArgs.fromBundle(requireArguments()).isBackToMainFragmentEnabled
    }

    override fun getTitleText(): String =
        requireContext().getString(R.string.notes_instructions_title)
    override fun getDescriptionText(): String =
        requireContext().getString(R.string.notes_instructions_description)

    override fun navigateToNextFragment() {
        mainActivity.navController
            .navigate(NotesInstructionsFragmentDirections
                .actionNotesInstructionsFragmentToStatusInstructionsFragment(isBackToMainFragmentEnabled))
    }

    override fun navigateToPreviousFragment() {
        mainActivity.navController
            .navigate(NotesInstructionsFragmentDirections
                .actionNotesInstructionsFragmentToLifeInstructionsFragment(isBackToMainFragmentEnabled))
    }

}