package com.martiserramolina.lifeplan.ui.fragments.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> onToolbarMenuClicked()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupToolbar() {
        mainActivity.apply {
            setSupportActionBar(binding.fragmentMainTb)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.ic_toolbar_menu)
            }
        }
        setHasOptionsMenu(true)
    }

    private fun setupNavigation() {
        setupNavigationView()
        navigateToNavSection()
    }

    private fun setupBackButton() {
        mainActivity.onBackPressedDispatcher
            .addCallback(mainActivity) { requireActivity().finish() }
    }

    private fun onToolbarMenuClicked(): Boolean {
        binding.fragmentMainDl.openDrawer(GravityCompat.START)
        return true
    }

    private fun setupNavigationView() {
        binding.fragmentMainNv.apply {
            isSaveEnabled = false
            setNavigationItemSelectedListener { onNavigationItemSelected(it) }
        }
    }

    private fun navigateToNavSection() {
        mainActivity.supportActionBar?.title = getString(viewModel.navSection.labelId)
        binding.fragmentMainNv.setCheckedItem(viewModel.navSection.destinationId)
        navController.navigate(viewModel.navSection.destinationId)
    }

    private fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        viewModel.navSection = NavSection.getNavSection(menuItem.itemId)
        navigateToNavSection()
        Handler(Looper.getMainLooper()).postDelayed({
            binding.fragmentMainDl.closeDrawer(GravityCompat.START)
        }, requireContext().resources.getInteger(R.integer.animation_start_offset).toLong())
        return true
    }
}