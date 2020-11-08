package com.martiserramolina.lifeplan.ui.fragments.instructions.sections.notes

import android.view.*
import com.martiserramolina.lifeplan.databinding.FragmentInstructionsSectionLifeBinding
import com.martiserramolina.lifeplan.databinding.FragmentInstructionsSectionNotesBinding
import com.martiserramolina.lifeplan.ui.fragments.instructions.sections.SectionInstructionsFragment

class NotesInstructionsFragment :
    SectionInstructionsFragment<FragmentInstructionsSectionNotesBinding>() {

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInstructionsSectionNotesBinding = FragmentInstructionsSectionNotesBinding.inflate(
        inflater, container, false
    )

    override fun navigateToNextFragment() {

    }

    override fun navigateToPreviousFragment() {

    }
}