package com.martiserramolina.lifeplan.ui.fragments.instructions.sections.status

import android.view.*
import com.martiserramolina.lifeplan.databinding.FragmentInstructionsSectionStatusBinding
import com.martiserramolina.lifeplan.ui.fragments.instructions.sections.SectionInstructionsFragment

class StatusInstructionsFragment :
    SectionInstructionsFragment<FragmentInstructionsSectionStatusBinding>() {

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInstructionsSectionStatusBinding = FragmentInstructionsSectionStatusBinding.inflate(
        inflater, container, false
    )

    override fun navigateToNextFragment() {

    }

    override fun navigateToPreviousFragment() {

    }
}