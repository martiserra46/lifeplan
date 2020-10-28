package com.martiserramolina.lifeplan.ui.fragments.up.situation.day.save.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavSituationDaySaveBinding
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.extensions.formatted
import com.martiserramolina.lifeplan.repository.enums.DaySatisfaction
import com.martiserramolina.lifeplan.ui.adapters.DaySatisfactionAdapter
import com.martiserramolina.lifeplan.ui.fragments.up.situation.day.save.UpSaveDayFragment
import com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day.add.AddDayViewModel
import java.util.*

class UpAddDayFragment : UpSaveDayFragment() {

    private val viewModel by lazy {
        ViewModelProvider(
            this, AddDayViewModel.Factory(requireActivity().application)
        ).get(AddDayViewModel::class.java)
    }

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavSituationDaySaveBinding {
        return FragmentNavSituationDaySaveBinding.inflate(inflater, container, false)
    }

    override fun getToolbar(): Toolbar = binding.fragmentNavSituationDaySaveTb

    override fun getToolbarTitle(): String = getString(R.string.situation_day_add)

    override fun navigateToPreviousFragment() {
        mainActivityNavController.navigate(
            AddDayFragmentDirections.actionAddDayFragmentToMainFragment(NavSection.SITUATION)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveDateToViewModel()
        setupDateTv()
        setupSatisfactionSp()
        navigateToPreviousFragmentAfterDbOp()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.situation_day_add_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.situation_day_add_save_mi -> {
                saveDataIfValid()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveDateToViewModel() {
        viewModel.day.dayDate = Date()
    }

    private fun setupDateTv() {
        binding.fragmentNavSituationDaySaveDateTv.text = getDate().formatted()
    }

    private fun setupSatisfactionSp() {
        binding.fragmentNavSituationDaySaveSatisfactionSp.apply {
            adapter = DaySatisfactionAdapter(
                requireContext(), R.layout.spinner_item
            ).apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
        }
    }

    private fun saveDataIfValid() {
        if (isDataValid()) saveData()
        else showInvalidDataMessage()
    }

    private fun isDataValid(): Boolean {
        return getText().isNotEmpty()
    }

    private fun saveData() {
        viewModel.day.apply {
            daySatisfaction = getSatisfaction()
            dayText = getText()
        }
        viewModel.addDay()
    }

    private fun showInvalidDataMessage() {
        Toast.makeText(context, "Invalid Data", Toast.LENGTH_SHORT).show()
    }

    private fun getDate(): Date {
        return viewModel.day.dayDate
    }

    private fun getSatisfaction(): DaySatisfaction {
        return binding.fragmentNavSituationDaySaveSatisfactionSp.selectedItem
            .run { this as DaySatisfaction }
    }

    private fun getText(): String {
        return binding.fragmentNavSituationDaySaveDescriptionEt.text.toString()
    }

    private fun navigateToPreviousFragmentAfterDbOp() {
        viewModel.dayAdded.observe(viewLifecycleOwner) { dayAdded ->
            if (dayAdded) navigateToPreviousFragment()
        }
    }
}