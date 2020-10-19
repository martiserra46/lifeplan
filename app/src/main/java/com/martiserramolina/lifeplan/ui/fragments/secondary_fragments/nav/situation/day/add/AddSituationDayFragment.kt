package com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.nav.situation.day.add

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavSituationDaySaveBinding
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.extensions.format
import com.martiserramolina.lifeplan.repository.enums.SituationDaySatisfaction
import com.martiserramolina.lifeplan.repository.model.SituationDay
import com.martiserramolina.lifeplan.ui.adapters.SituationDaySatisfactionAdapter
import com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.SecondaryFragment
import com.martiserramolina.lifeplan.viewmodels.situation.day.add.AddSituationDayViewModel
import java.util.*

class AddSituationDayFragment : SecondaryFragment<FragmentNavSituationDaySaveBinding>() {

    private val viewModel by lazy {
        ViewModelProvider(
            this, AddSituationDayViewModel.Factory(requireActivity().application)
        ).get(AddSituationDayViewModel::class.java)
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavSituationDaySaveBinding {
        return FragmentNavSituationDaySaveBinding.inflate(inflater, container, false)
    }

    override fun getToolbar(): Toolbar = binding.fragmentNavSituationDaySaveTb

    override fun getToolbarTitle(): String = getString(R.string.situation_day_add)

    override fun navigateToPreviousFragment() {
        navController.navigate(
            AddSituationDayFragmentDirections
                .actionAddSituationDayFragmentToMainFragment(NavSection.SITUATION)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveDateToViewModel()
        setupDateTextView()
        setupSatisfactionSpinner()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.situation_day_add_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.situation_day_add_save_mi -> {
                saveSituationDay()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveDateToViewModel() {
        viewModel.date = Date()
    }

    private fun setupDateTextView() {
        viewModel.date = Date()
        binding.fragmentNavSituationDaySaveDateTv.text = viewModel.date.format("dd/mm/yyyy")
    }

    private fun setupSatisfactionSpinner() {
        binding.fragmentNavSituationDaySaveSatisfactionSp.apply {
            adapter = SituationDaySatisfactionAdapter(
                requireContext(), R.layout.spinner_item
            ).apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
        }
    }

    private fun saveSituationDay() {
        viewModel.insertSituationDay(SituationDay(getDate(), getDescription(), getSatisfaction()))
        navigateToPreviousFragment()
    }

    private fun getDate(): Date {
        return viewModel.date
    }

    private fun getSatisfaction(): SituationDaySatisfaction {
        return binding.fragmentNavSituationDaySaveSatisfactionSp.selectedItem
            .run { this as SituationDaySatisfaction }
    }

    private fun getDescription(): String {
        return binding.fragmentNavSituationDaySaveDescriptionEt.text.toString()
    }
}