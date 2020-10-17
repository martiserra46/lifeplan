package com.martiserramolina.lifeplan.ui.fragments.nav.your_life.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavYourLifeSaveBinding
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.ui.activities.MainActivity
import com.martiserramolina.lifeplan.viewmodels.your_life.YourLifeViewModel
import com.martiserramolina.lifeplan.viewmodels.your_life.edit.EditYourLifeViewModel

class EditYourLifeFragment : Fragment() {

    private lateinit var binding: FragmentNavYourLifeSaveBinding
    private val mainActivity by lazy { activity as MainActivity }
    private val viewModel by lazy { buildViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavYourLifeSaveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupActionBar()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                navigateToMainFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun buildViewModel(): EditYourLifeViewModel {
        return ViewModelProvider(
            this, EditYourLifeViewModel.Factory(requireActivity().application)
        ).get(EditYourLifeViewModel::class.java)
    }

    private fun setupActionBar() {
        mainActivity.apply {
            setSupportActionBar(binding.fragmentNavYourLifeSaveTb)
            supportActionBar?.apply {
                title = getString(R.string.your_life_edit)
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.ic_back)
            }
        }
        setHasOptionsMenu(true)
    }

    private fun navigateToMainFragment() {
        mainActivity.navController.navigate(
            EditYourLifeFragmentDirections
                .actionEditYourLifeFragmentToMainFragment(NavSection.YOUR_LIFE)
        )
    }
}