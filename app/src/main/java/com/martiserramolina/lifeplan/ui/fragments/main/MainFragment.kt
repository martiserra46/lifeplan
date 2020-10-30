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

class MainFragment : BaseFragment<FragmentMainBinding>() {

    companion object { private const val NAV_SECTION_KEY = "navSection" }

    private val navController by lazy {
        childFragmentManager.findFragmentById(R.id.fragment_main_fcv)
            .run { this as NavHostFragment }.navController
    }

    private lateinit var navSection: NavSection

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupNavigation(savedInstanceState)
        setupBackButton()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> onToolbarMenuClicked()
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(NAV_SECTION_KEY, navSection)
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

    private fun setupNavigation(savedInstanceState: Bundle?) {
        navSection = getFirstNavSection(savedInstanceState)
        navigateToNavSection()
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

    private fun getFirstNavSection(savedInstanceState: Bundle?): NavSection {
        return if (savedInstanceState == null) {
            MainFragmentArgs.fromBundle(requireArguments()).navSection
        } else {
            savedInstanceState.getSerializable(NAV_SECTION_KEY) as NavSection
        }
    }

    private fun navigateToNavSection() {
        mainActivity.supportActionBar?.title = getString(navSection.labelId)
        binding.fragmentMainNv.setCheckedItem(navSection.destinationId)
        navController.navigate(navSection.destinationId)
    }

    private fun setupNavigationView() {
        binding.fragmentMainNv.setNavigationItemSelectedListener { onNavigationItemSelected(it) }
    }

    private fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        navSection = NavSection.getNavSection(menuItem.itemId)
        navigateToNavSection()
        Handler(Looper.getMainLooper()).postDelayed({
            binding.fragmentMainDl.closeDrawer(GravityCompat.START)
        }, requireContext().resources.getInteger(R.integer.animation_start_offset).toLong())
        return true
    }
}