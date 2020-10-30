package com.martiserramolina.lifeplan.ui.fragments.nav.situation

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavSituationBinding
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.situation.day.DayAdapter
import com.martiserramolina.lifeplan.ui.fragments.main.MainFragmentDirections
import com.martiserramolina.lifeplan.ui.fragments.nav.NavFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.info.InfoSituationViewModel

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
        binding.fragmentNavSituationRv.apply {
            adapter = DayAdapter { navigateToDayFragment(it) }
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                    setDrawable(ContextCompat.getDrawable(context, R.drawable.div_rvi)!!)
                }
            )
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    onScrollDaysRv()
                }
            })
        }

        viewModel.itemsFetched.observe(viewLifecycleOwner) { days ->
            binding.fragmentNavSituationRv.apply {
                adapter.run { this as DayAdapter }.items = days
            }
        }
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

    private fun onScrollDaysRv() {
        val lastVisibleDayPosition = binding.fragmentNavSituationRv.layoutManager
            .run { this as LinearLayoutManager }.findLastVisibleItemPosition()
        val lastDayPositionRv = binding.fragmentNavSituationRv.adapter
            .run { this as DayAdapter }.items.size - 1
        if (lastVisibleDayPosition == lastDayPositionRv) {
            viewModel.fetchItemsIfNotFetched((lastDayPositionRv + 1).toLong())
        }
    }
}