package com.martiserramolina.lifeplan.ui.fragments.up.situation.day.info

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavSituationDayBinding
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.extensions.formatted
import com.martiserramolina.lifeplan.ui.fragments.up.UpFragment
import com.martiserramolina.lifeplan.ui.fragments.up.situation.day.DayFragmentArgs
import com.martiserramolina.lifeplan.ui.fragments.up.situation.day.DayFragmentDirections
import com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day.DayViewModel

class UpInfoDayFragment : UpFragment<FragmentNavSituationDayBinding>() {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            DayViewModel.Factory(
                DayFragmentArgs.fromBundle(requireArguments()).day,
                requireActivity().application
            )
        ).get(DayViewModel::class.java)
    }

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavSituationDayBinding {
        return FragmentNavSituationDayBinding.inflate(inflater, container, false)
    }

    override fun getToolbar(): Toolbar = binding.fragmentNavSituationDayTb

    override fun getToolbarTitle(): String = ""

    override fun navigateToPreviousFragment() {
        navController.navigate(
            DayFragmentDirections.actionDayFragmentToMainFragment(NavSection.SITUATION)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDateTv()
        setupSatisfactionTv()
        setupDescriptionTv()
        navigateToPreviousFragmentAfterDbOp()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.situation_day_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.situation_day_edit_mi -> {
                navigateToEditDayFragment()
                true
            }
            R.id.situation_day_delete_mi -> {
                deleteDay()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupDateTv() {
        binding.fragmentNavSituationDayDateTv.text = viewModel.day
            .dayDate.formatted()
    }

    private fun setupSatisfactionTv() {
        viewModel.day.daySatisfaction.let { satisfaction ->
            binding.fragmentNavSituationDaySatisfactionTv.apply {
                text = getString(satisfaction.stringId)
                setTextColor(ContextCompat.getColor(context, satisfaction.colorId))
            }
        }
    }

    private fun setupDescriptionTv() {
        binding.fragmentNavSituationDayDescriptionTv.text = viewModel.day.dayText
    }

    private fun navigateToEditDayFragment() {
        navController.navigate(
            DayFragmentDirections.actionDayFragmentToEditDayFragment(viewModel.day)
        )
    }

    private fun deleteDay() {
        viewModel.deleteDay()
    }

    private fun navigateToPreviousFragmentAfterDbOp() {
        viewModel.dayDeleted.observe(viewLifecycleOwner) { dayDeleted ->
            if (dayDeleted) navigateToPreviousFragment()
        }
    }
}