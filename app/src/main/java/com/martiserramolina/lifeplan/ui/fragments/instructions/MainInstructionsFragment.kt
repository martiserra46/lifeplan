package com.martiserramolina.lifeplan.ui.fragments.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.martiserramolina.lifeplan.databinding.FragmentInstructionsMainBinding
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment

class MainInstructionsFragment : BaseFragment<FragmentInstructionsMainBinding>() {
    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInstructionsMainBinding = FragmentInstructionsMainBinding.inflate(
        inflater, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBackButton()
    }

    private fun setupBackButton() {
        mainActivity.onBackPressedDispatcher
            .addCallback(mainActivity) {
                mainActivity.navController.navigate(
                    MainInstructionsFragmentDirections
                        .actionMainInstructionsFragmentToMainFragment())
            }
    }
}