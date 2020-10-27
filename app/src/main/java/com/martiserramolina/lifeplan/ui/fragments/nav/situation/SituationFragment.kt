package com.martiserramolina.lifeplan.ui.fragments.nav.situation

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavSituationBinding
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.ui.activities.MainActivity
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment
import com.martiserramolina.lifeplan.ui.adapters.DayAdapter
import com.martiserramolina.lifeplan.ui.fragments.MainFragmentDirections
import com.martiserramolina.lifeplan.ui.fragments.interfaces.OnAddMenuItemClickListener
import com.martiserramolina.lifeplan.viewmodels.nav.situation.SituationViewModel

class SituationFragment : BaseFragment<FragmentNavSituationBinding>(), OnAddMenuItemClickListener {

    private val mainActivity by lazy { activity as MainActivity }

    private val viewModel by lazy {
        ViewModelProvider(
            this, SituationViewModel.Factory(requireActivity().application)
        ).get(SituationViewModel::class.java)
    }

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavSituationBinding {
        return FragmentNavSituationBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupDaysRv()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.situation_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.situation_add_mi -> onAddMenuItemClicked()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupDaysRv() {
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

        viewModel.days.observe(viewLifecycleOwner) { days ->
            binding.fragmentNavSituationRv.apply {
                adapter.run { this as DayAdapter }.listDays = days
            }
        }
    }

    override fun onAddMenuItemClicked(): Boolean {
        navigateToAddDayFragment()
        return true
    }

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
            .run { this as DayAdapter }.listDays.size - 1
        if (lastVisibleDayPosition == lastDayPositionRv) {
            viewModel.fetchDaysFromPositionIfNotFetched(lastDayPositionRv + 1)
        }
    }
}