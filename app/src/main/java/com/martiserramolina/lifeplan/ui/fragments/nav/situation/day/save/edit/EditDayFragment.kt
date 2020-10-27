package com.martiserramolina.lifeplan.ui.fragments.nav.situation.day.save.edit

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavSituationDaySaveBinding
import com.martiserramolina.lifeplan.extensions.formatted
import com.martiserramolina.lifeplan.repository.enums.DaySatisfaction
import com.martiserramolina.lifeplan.ui.adapters.DaySatisfactionAdapter
import com.martiserramolina.lifeplan.ui.fragments.abstracts.UpButtonFragment
import com.martiserramolina.lifeplan.viewmodels.nav.situation.day.save.edit.EditDayViewModel

class EditDayFragment : UpButtonFragment<FragmentNavSituationDaySaveBinding>() {

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
        navigateToPreviousFragmentAfterDbOp()
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
        binding.fragmentNavSituationDaySaveDateTv.text = viewModel.day.dayDate.formatted()
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
        viewModel.day.apply {
            daySatisfaction = getSatisfaction()
            dayText = getText()
        }
        viewModel.editDay()
    }

    private fun getSatisfaction(): DaySatisfaction {
        return binding.fragmentNavSituationDaySaveSatisfactionSp.selectedItem
            .run { this as DaySatisfaction }
    }

    private fun getText(): String {
        return binding.fragmentNavSituationDaySaveDescriptionEt.text.toString()
    }

    private fun navigateToPreviousFragmentAfterDbOp() {
        viewModel.dayEdited.observe(viewLifecycleOwner) { dayEdited ->
            if (dayEdited) navigateToPreviousFragment()
        }
    }
}