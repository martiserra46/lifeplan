package com.martiserramolina.lifeplan.ui.fragments.sections.nav.status

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavStatusBinding
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.day.DayAdapter
import com.martiserramolina.lifeplan.ui.fragments.main.MainFragmentDirections
import com.martiserramolina.lifeplan.ui.fragments.sections.nav.NavFragment
import com.martiserramolina.lifeplan.utils.interfaces.LoadItemsFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.status.info.InfoStatusViewModel

class NavStatusFragment : NavFragment<FragmentNavStatusBinding>(), LoadItemsFragment {

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
        setupItemsRecyclerView(
            binding.fragmentNavStatusRv,
            DayAdapter { navigateToDayFragment(it) },
            viewModel,
            viewLifecycleOwner,
            binding.fragmentNavStatusEmptyCl
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.status_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.status_add_mi -> onAddMenuItemSelected { navigateToAddDayFragment() }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToDayFragment(day: Day) {
        mainActivity.navController.navigate(
            MainFragmentDirections
                .actionMainFragmentToDayFragment(day)
        )
    }

    private fun navigateToAddDayFragment() {
        mainActivity.navController
            .navigate(MainFragmentDirections.actionMainFragmentToAddDayFragment())
    }
}