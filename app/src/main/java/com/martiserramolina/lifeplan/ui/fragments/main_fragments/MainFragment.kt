package com.martiserramolina.lifeplan.ui.fragments.main_fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentMainBinding
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.ui.activities.MainActivity
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val mainActivity by lazy { activity as MainActivity }

    private val navController: NavController
        get() = childFragmentManager.findFragmentById(
            R.id.fragment_main_fcv
        ).run { this as NavHostFragment }.navController

    private lateinit var navSection: NavSection

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActionBar()
        setupNavSection()
        setupNavView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                binding.fragmentMainDl.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupActionBar() {
        mainActivity.apply {
            setSupportActionBar(binding.fragmentMainTb)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.ic_toolbar_menu)
            }
        }
        setHasOptionsMenu(true)
    }

    private fun setupNavSection() {
        navSection = MainFragmentArgs.fromBundle(requireArguments()).navSection
        mainActivity.supportActionBar?.title = getString(navSection.label)
        if (navSection != NavSection.LIFE) navigateToNavSection(navSection)
    }

    private fun setupNavView() {
        binding.fragmentMainNv.setNavigationItemSelectedListener { menuItem ->
            navSection = NavSection.getNavSection(menuItem.itemId)
            navigateToNavSection(navSection)
            Handler(Looper.getMainLooper()).postDelayed({
                binding.fragmentMainDl.closeDrawers()
            }, 100)
            true
        }
    }

    private fun navigateToNavSection(navSection: NavSection) {
        mainActivity.supportActionBar?.title = getString(navSection.label)
        binding.fragmentMainNv.setCheckedItem(navSection.destinationId)
        navController.navigate(navSection.destinationId)
    }
}