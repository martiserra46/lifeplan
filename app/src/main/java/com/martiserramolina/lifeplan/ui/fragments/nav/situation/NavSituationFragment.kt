package com.martiserramolina.lifeplan.ui.fragments.nav.situation

import android.os.Bundle
import android.view.*
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavSituationBinding
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.adapters.recyclerview.adapters.situation.day.DayAdapter
import com.martiserramolina.lifeplan.extensions.setupAutoLoadItemsFunctionality
import com.martiserramolina.lifeplan.ui.fragments.main.MainFragmentDirections
import com.martiserramolina.lifeplan.ui.fragments.nav.NavFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.situation.info.InfoSituationViewModel

class NavSituationFragment : NavFragment<FragmentNavSituationBinding>() {

    private val viewModel by ViewModelFactory.Delegate(
        this, InfoSituationViewModel::class.java
    ) { InfoSituationViewModel(mainActivity.application) }

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavSituationBinding = FragmentNavSituationBinding.inflate(
        inflater, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupDaysRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.situation_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.situation_add_mi -> onAddMenuItemSelected()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupDaysRecyclerView() {
        binding.fragmentNavSituationRv.setupAutoLoadItemsFunctionality(
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