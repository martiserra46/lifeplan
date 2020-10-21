package com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.nav.situation.day.edit

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavSituationDaySaveBinding
import com.martiserramolina.lifeplan.extensions.format
import com.martiserramolina.lifeplan.repository.enums.DaySatisfaction
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.ui.adapters.DaySatisfactionAdapter
import com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.SecondaryFragment
import com.martiserramolina.lifeplan.viewmodels.situation.day.edit.EditDayViewModel
import java.util.*

class EditDayFragment : SecondaryFragment<FragmentNavSituationDaySaveBinding>() {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            EditDayViewModel.Factory(
                EditDayFragmentArgs.fromBundle(requireArguments()).day,
                requireActivity().application
            )
        ).get(EditDayViewModel::class.java)
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavSituationDaySaveBinding {
        return FragmentNavSituationDaySaveBinding.inflate(inflater, container, false)
    }

    override fun getToolbar(): Toolbar = binding.fragmentNavSituationDaySaveTb

    override fun getToolbarTitle(): String = getString(R.string.edit)

    override fun navigateToPreviousFragment() {
        navController
            .navigate(EditDayFragmentDirections.actionEditDayFragmentToDayFragment(viewModel.day))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDateTv()
        setupSatisfactionSp()
        setupTextTv()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.situation_day_edit_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.situation_day_edit_save_mi -> {
                saveDay()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupDateTv() {
        binding.fragmentNavSituationDaySaveDateTv.text = viewModel.day.dayDate.format("dd/mm/yyyy")
    }

    private fun setupSatisfactionSp() {
        binding.fragmentNavSituationDaySaveSatisfactionSp.apply {
            adapter = DaySatisfactionAdapter(
                requireContext(), R.layout.spinner_item
            ).apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
            setSelection(viewModel.day.daySatisfaction.ordinal)
        }
    }

    private fun setupTextTv() {
        binding.fragmentNavSituationDaySaveDescriptionEt.setText(viewModel.day.dayText)
    }

    private fun saveDay() {
        viewModel.day = Day(viewModel.day.dayId, getDate(), getDescription(), getSatisfaction())
        viewModel.updateDay(viewModel.day)
        navigateToPreviousFragment()
    }

    private fun getDate(): Date {
        return viewModel.day.dayDate
    }

    private fun getSatisfaction(): DaySatisfaction {
        return binding.fragmentNavSituationDaySaveSatisfactionSp.selectedItem
            .run { this as DaySatisfaction }
    }

    private fun getDescription(): String {
        return binding.fragmentNavSituationDaySaveDescriptionEt.text.toString()
    }
}