package com.martiserramolina.lifeplan.ui.fragments.instructions

import android.view.LayoutInflater
import android.view.ViewGroup
import com.martiserramolina.lifeplan.databinding.FragmentInstructionsMainBinding
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment

class MainInstructionsFragment : BaseFragment<FragmentInstructionsMainBinding>() {
    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInstructionsMainBinding = FragmentInstructionsMainBinding.inflate(
        inflater, container, false
    )
}