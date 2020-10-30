package com.martiserramolina.lifeplan.ui.fragments.up.situation.day.save

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavSituationDaySaveBinding
import com.martiserramolina.lifeplan.extensions.formatted
import com.martiserramolina.lifeplan.repository.enums.DaySatisfaction
import com.martiserramolina.lifeplan.adapters.spinner.day_satisfaction.DaySatisfactionAdapter
import com.martiserramolina.lifeplan.functions.showMessage
import com.martiserramolina.lifeplan.ui.fragments.up.situation.day.UpDayFragment
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.situation.day.save.SaveDayViewModel

abstract class UpSaveDayFragment() : UpDayFragment<FragmentNavSituationDaySaveBinding>() {

    protected abstract val viewModel: SaveDayViewModel

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavSituationDaySaveBinding = FragmentNavSituationDaySaveBinding.inflate(
        inflater, container, false
    )

    override fun getToolbar(): Toolbar = binding.fragmentNavSituationDaySaveTb

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupWhenDaySavedFunctionality()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(getMenuResourceId(), menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            getSaveMenuItemId() -> onSaveMenuItemSelected()
            else -> super.onOptionsItemSelected(item)
        }
    }

    protected open fun setupViews() {
        setupDateTextView()
        setupSatisfactionSpinner()
        setupDescriptionTextView()
    }

    private fun setupWhenDaySavedFunctionality() {
        viewModel.daySaved.observe(viewLifecycleOwner) { daySaved ->
            if (daySaved) {
                navigateToPreviousFragment()
                showMessage(binding.root, getDaySavedMessage())
            }
        }
    }

    protected abstract fun getMenuResourceId(): Int

    protected abstract fun getSaveMenuItemId(): Int

    private fun onSaveMenuItemSelected(): Boolean = saveDayIfValid().run { true }

    private fun setupDateTextView() {
        binding.fragmentNavSituationDaySaveDateTv.text = viewModel.day.dayDate.formatted()
    }

    private fun setupSatisfactionSpinner() {
        binding.fragmentNavSituationDaySaveSatisfactionSp.apply {
            adapter = DaySatisfactionAdapter(requireContext())
            setSelection(viewModel.day.daySatisfaction.ordinal)
        }
    }

    private fun setupDescriptionTextView() {
        binding.fragmentNavSituationDaySaveDescriptionEt.setText(viewModel.day.dayText)
    }

    private fun saveDayIfValid() {
        if (isDayValid()) saveDay() else showInvalidDayMessage()
    }

    private fun isDayValid(): Boolean = getDescriptionFromEditText().isNotEmpty()

    private fun saveDay() {
        viewModel.day.apply {
            daySatisfaction = getSatisfactionFromSpinner()
            dayText = getDescriptionFromEditText()
        }
        viewModel.saveDay()
    }

    protected abstract fun getDaySavedMessage(): Int

    private fun showInvalidDayMessage() {
        Toast.makeText(context, getString(R.string.invalid_day), Toast.LENGTH_SHORT).show()
    }

    private fun getSatisfactionFromSpinner(): DaySatisfaction {
        return binding.fragmentNavSituationDaySaveSatisfactionSp.selectedItem
            .run { this as DaySatisfaction }
    }

    private fun getDescriptionFromEditText(): String {
        return binding.fragmentNavSituationDaySaveDescriptionEt.text.toString()
    }
}