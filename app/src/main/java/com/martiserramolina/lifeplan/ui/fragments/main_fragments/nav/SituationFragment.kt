package com.martiserramolina.lifeplan.ui.fragments.main_fragments.nav

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavSituationBinding
import com.martiserramolina.lifeplan.repository.model.Day
import com.martiserramolina.lifeplan.ui.activities.MainActivity
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment
import com.martiserramolina.lifeplan.ui.adapters.DayAdapter
import com.martiserramolina.lifeplan.ui.fragments.main_fragments.MainFragmentDirections
import com.martiserramolina.lifeplan.viewmodels.situation.SituationViewModel

class SituationFragment : BaseFragment<FragmentNavSituationBinding>() {

    private val mainActivity by lazy { activity as MainActivity }

    private val viewModel by lazy {
        ViewModelProvider(
            this, SituationViewModel.Factory(requireActivity().application)
        ).get(SituationViewModel::class.java)
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavSituationBinding {
        return FragmentNavSituationBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.situation_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.situation_add_mi -> {
                navigateToAddSituationDayFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupRecyclerView() {
        binding.fragmentNavSituationRv.apply {
            adapter = DayAdapter { situationDayId, situationDay ->
                navigateToSituationDayFragment(situationDayId, situationDay)
            }
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                    setDrawable(ContextCompat.getDrawable(context, R.drawable.div_rvi)!!)
                }
            )
        }

        viewModel.situationDays.observe(viewLifecycleOwner) { situationDays ->
            binding.fragmentNavSituationRv.apply {
                adapter.run { this as DayAdapter }.listSituationDays = situationDays
            }
        }
    }

    private fun navigateToAddSituationDayFragment() {
        mainActivity.navController
            .navigate(MainFragmentDirections.actionMainFragmentToAddSituationDayFragment())
    }

    private fun navigateToSituationDayFragment(situationDayId: Long, situationDay: Day) {
        mainActivity.navController.navigate(
            MainFragmentDirections
                .actionMainFragmentToSituationDayFragment(situationDayId, situationDay)
        )
    }
}