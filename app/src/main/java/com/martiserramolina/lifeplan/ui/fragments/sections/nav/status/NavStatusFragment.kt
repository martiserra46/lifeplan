package com.martiserramolina.lifeplan.ui.fragments.sections.nav.status

import android.os.Bundle
import android.view.*
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavStatusBinding
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.day.DayAdapter
import com.martiserramolina.lifeplan.ui.fragments.main.MainFragmentDirections
import com.martiserramolina.lifeplan.ui.fragments.sections.nav.NavFragment
import com.martiserramolina.lifeplan.utils.functions.setupAutoLoadItemsFunctionality
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.status.info.InfoStatusViewModel

class NavStatusFragment : NavFragment<FragmentNavStatusBinding>() {

    private val viewModel by ViewModelFactory.Delegate(
        this, InfoStatusViewModel::class.java
    ) { InfoStatusViewModel(mainActivity.application) }

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavStatusBinding = FragmentNavStatusBinding.inflate(
        inflater, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupDaysRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.status_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.status_add_mi -> onAddMenuItemSelected()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupDaysRecyclerView() {
        binding.fragmentNavStatusRv.setupAutoLoadItemsFunctionality(
            viewLifecycleOwner, DayAdapter { navigateToDayFragment(it) }, viewModel
        )
    }

    private fun onAddMenuItemSelected(): Boolean = navigateToAddDayFragment().run { true }

    private fun navigateToAddDayFragment() {
        mainActivity.navController
            .navigate(MainFragmentDirections.actionMainFragmentToAddDayFragment())
    }

    private fun navigateToDayFragment(day: Day) {
        mainActivity.navController.navigate(
            MainFragmentDirections
                .actionMainFragmentToDayFragment(day)
        )
    }
}