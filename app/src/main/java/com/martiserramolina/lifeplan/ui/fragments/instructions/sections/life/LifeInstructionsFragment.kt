package com.martiserramolina.lifeplan.ui.fragments.instructions.sections.life

import android.view.*
import com.martiserramolina.lifeplan.databinding.FragmentInstructionsSectionLifeBinding
import com.martiserramolina.lifeplan.ui.fragments.instructions.sections.SectionInstructionsFragment

class LifeInstructionsFragment :
    SectionInstructionsFragment<FragmentInstructionsSectionLifeBinding>() {

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInstructionsSectionLifeBinding = FragmentInstructionsSectionLifeBinding.inflate(
        inflater, container, false
    )

    override fun navigateToNextFragment() {

    }

    override fun navigateToPreviousFragment() {

    }
}