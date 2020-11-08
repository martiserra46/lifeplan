package com.martiserramolina.lifeplan.ui.fragments.instructions

import android.view.LayoutInflater
import android.view.ViewGroup
import com.martiserramolina.lifeplan.databinding.FragmentInstructionsBinding
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment

class InstructionsFragment : BaseFragment<FragmentInstructionsBinding>() {

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInstructionsBinding = FragmentInstructionsBinding.inflate(
        inflater, container, false
    )
}