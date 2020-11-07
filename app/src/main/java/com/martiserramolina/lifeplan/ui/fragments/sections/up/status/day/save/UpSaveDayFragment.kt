package com.martiserramolina.lifeplan.ui.fragments.sections.up.status.day.save

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavStatusDaySaveBinding
import com.martiserramolina.lifeplan.utils.functions.formatted
import com.martiserramolina.lifeplan.repository.enums.DaySatisfaction
import com.martiserramolina.lifeplan.ui.adapters.spinner.day_satisfaction.DaySatisfactionAdapter
import com.martiserramolina.lifeplan.utils.functions.showMessage
import com.martiserramolina.lifeplan.ui.fragments.sections.up.status.day.UpDayFragment
import com.martiserramolina.lifeplan.utils.interfaces.SaveItemFragment
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.status.day.save.SaveDayViewModel

abstract class UpSaveDayFragment() :
    UpDayFragment<FragmentNavStatusDaySaveBinding>(),
    SaveItemFragment {

    protected abstract val viewModel: SaveDayViewModel

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavStatusDaySaveBinding = FragmentNavStatusDaySaveBinding.inflate(
        inflater, container, false
    )

    override fun getToolbar(): Toolbar = binding.fragmentNavStatusDaySaveTb

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews {
            setupDateTextView()
            setupSatisfactionSpinner()
            setupDescriptionTextView()
        }
        setupWhenItemSavedFunctionality(viewModel, viewLifecycleOwner) {
            navigateToPreviousFragment()
            showMessage(binding.root, getDaySavedMessage())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(getMenuResourceId(), menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            getSaveMenuItemId() -> onSaveMenuItemSelected(
                ::isDayValid, ::saveDay, ::showMessageInvalidDay)
            else -> super.onOptionsItemSelected(item)
        }
    }

    protected abstract fun getMenuResourceId(): Int

    protected abstract fun getSaveMenuItemId(): Int

    private fun setupDateTextView() {
        binding.fragmentNavStatusDaySaveDateTv.text = viewModel.day.dayDate.formatted()
    }

    private fun setupSatisfactionSpinner() {
        binding.fragmentNavStatusDaySaveSatisfactionSp.apply {
            adapter = DaySatisfactionAdapter(requireContext())
            setSelection(viewModel.day.daySatisfaction.ordinal)
        }
    }

    private fun setupDescriptionTextView() {
        binding.fragmentNavStatusDaySaveDescriptionEt.setText(viewModel.day.dayText)
    }

    private fun isDayValid(): Boolean = getDescriptionFromEditText().isNotEmpty()

    private fun saveDay() {
        viewModel.day.apply {
            daySatisfaction = getSatisfactionFromSpinner()
            dayText = getDescriptionFromEditText()
        }
        viewModel.saveItem()
    }

    private fun showMessageInvalidDay() {
        showMessage(binding.root, R.string.invalid_day)
    }

    protected abstract fun getDaySavedMessage(): Int

    private fun getSatisfactionFromSpinner(): DaySatisfaction {
        return binding.fragmentNavStatusDaySaveSatisfactionSp.selectedItem
            .run { this as DaySatisfaction }
    }

    private fun getDescriptionFromEditText(): String {
        return binding.fragmentNavStatusDaySaveDescriptionEt.text.toString()
    }
}