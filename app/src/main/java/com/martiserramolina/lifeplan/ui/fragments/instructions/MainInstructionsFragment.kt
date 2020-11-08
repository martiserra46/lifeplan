package com.martiserramolina.lifeplan.ui.fragments.instructions

import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentInstructionsMainBinding
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment

class MainInstructionsFragment : BaseFragment<FragmentInstructionsMainBinding>() {

    val isBackToMainFragmentEnabled by lazy {
        MainInstructionsFragmentArgs.fromBundle(requireArguments()).isBackToMainFragmentEnabled
    }

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInstructionsMainBinding = FragmentInstructionsMainBinding.inflate(
        inflater, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupBackButton()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_instructions_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.main_instructions_next_mi -> navigateToInstructionsFragment().run { true }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupToolbar() {
        mainActivity.apply {
            setSupportActionBar(binding.fragmentInstructionsMainTb)
            supportActionBar?.title = ""
        }
        setHasOptionsMenu(true)
    }

    private fun navigateToInstructionsFragment() {
        mainActivity.navController
            .navigate(MainInstructionsFragmentDirections
                .actionMainInstructionsFragmentToLifeInstructionsFragment(isBackToMainFragmentEnabled))
    }

    private fun setupBackButton() {
        mainActivity.onBackPressedDispatcher
            .addCallback(mainActivity) {
                if (isBackToMainFragmentEnabled) {
                    mainActivity.navController
                        .navigate(MainInstructionsFragmentDirections.actionGlobalMainFragmentPop())
                }
            }
    }
}