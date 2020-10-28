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
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentMainBinding
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.ui.activities.MainActivity
import com.martiserramolina.lifeplan.ui.fragments.MainFragmentArgs
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>() {
    private val navController: NavController
        get() = childFragmentManager.findFragmentById(
            R.id.fragment_main_fcv
        ).run { this as NavHostFragment }.navController

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
        navigateToNavSection(MainFragmentArgs.fromBundle(requireArguments()).navSection)
        setupNavigationView()
    }

    private fun setupBackButton() {
        mainActivity.onBackPressedDispatcher
            .addCallback(mainActivity) { requireActivity().finish() }
    }

    private fun onToolbarMenuClicked(): Boolean {
        binding.fragmentMainDl.openDrawer(GravityCompat.START)
        return true
    }

    private fun navigateToNavSection(navSection: NavSection) {
        mainActivity.supportActionBar?.title = getString(navSection.label)
        binding.fragmentMainNv.setCheckedItem(navSection.destinationId)
        navController.navigate(navSection.destinationId)
    }

    private fun setupNavigationView() {
        binding.fragmentMainNv.setNavigationItemSelectedListener { onNavigationItemSelected(it) }
    }

    private fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        navigateToNavSection(NavSection.getNavSection(menuItem.itemId))
        Handler(Looper.getMainLooper()).postDelayed({
            binding.fragmentMainDl.closeDrawer(GravityCompat.START)
        }, requireContext().resources.getInteger(R.integer.animation_start_offset).toLong())
        return true
    }
}