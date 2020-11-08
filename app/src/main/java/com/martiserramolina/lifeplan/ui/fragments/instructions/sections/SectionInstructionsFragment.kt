package com.martiserramolina.lifeplan.ui.fragments.instructions.sections

import android.view.LayoutInflater
import android.view.ViewGroup
import com.martiserramolina.lifeplan.databinding.FragmentInstructionsSectionBinding
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment

class SectionInstructionsFragment : BaseFragment<FragmentInstructionsSectionBinding>() {
    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInstructionsSectionBinding = FragmentInstructionsSectionBinding.inflate(
        inflater, container, false
    )
}