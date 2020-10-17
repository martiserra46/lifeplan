package com.martiserramolina.lifeplan.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentMainBinding
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.ui.activities.MainActivity

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val mainActivity by lazy { activity as MainActivity }

    private val navController: NavController
        get() = childFragmentManager.findFragmentById(
            R.id.fragment_main_fcv
        ).run { this as NavHostFragment }.navController

    private lateinit var navSection: NavSection

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
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
        if (navSection != NavSection.YOUR_LIFE)
            navController.navigate(navSection.destinationId)
    }

    private fun setupNavView() {
        binding.fragmentMainNv.setNavigationItemSelectedListener { menuItem ->
            navSection = NavSection.getNavSection(menuItem.itemId)
            mainActivity.supportActionBar?.title = getString(navSection.label)
            menuItem.isChecked = true
            binding.fragmentMainDl.closeDrawers()
            navController.navigate(navSection.destinationId)
            true
        }
    }
}