package com.martiserramolina.lifeplan.ui.fragments.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentMainBinding
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.main.MainViewModel

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel by ViewModelFactory.Delegate(
        this, MainViewModel::class.java
    ) {
        val args = MainFragmentArgs.fromBundle(requireArguments())
        MainViewModel(args.navSection)
    }

    private val navController by lazy {
        childFragmentManager.findFragmentById(R.id.fragment_main_fcv)
            .run { this as NavHostFragment }.navController
    }

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupNavigation()
        setupBackButton()
    }

    private fun setupToolbar() {
        mainActivity.setSupportActionBar(binding.fragmentMainTb)
        setHasOptionsMenu(true)
    }

    private fun setupNavigation() {
        binding.fragmentMainBn.apply {
            setupWithNavController(navController)
            selectedItemId = viewModel.navSection.destinationId
            setOnNavigationItemSelectedListener {
                viewModel.navSection = NavSection.getNavSection(it.itemId)
                navController.navigate(viewModel.navSection.destinationId)
                true
            }
        }
        mainActivity.setupActionBarWithNavController(
            navController, AppBarConfiguration(binding.fragmentMainBn.menu)
        )
    }

    private fun setupBackButton() {
        mainActivity.onBackPressedDispatcher
            .addCallback(mainActivity) { requireActivity().finish() }
    }
}