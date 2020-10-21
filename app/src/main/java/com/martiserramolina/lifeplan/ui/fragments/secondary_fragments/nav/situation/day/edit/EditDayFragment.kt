package com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.nav.situation.day.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavSituationDaySaveBinding
import com.martiserramolina.lifeplan.extensions.format
import com.martiserramolina.lifeplan.repository.enums.DaySatisfaction
import com.martiserramolina.lifeplan.ui.adapters.DaySatisfactionAdapter
import com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.SecondaryFragment
import com.martiserramolina.lifeplan.viewmodels.situation.day.add.AddDayViewModel
import com.martiserramolina.lifeplan.viewmodels.situation.day.edit.EditDayViewModel

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
        navController.navigateUp()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDateTv()
        setupSatisfactionSp()
        setupTextTv()
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
        binding.fragmentNavSituationDaySaveDateTv.text = viewModel.day.dayText
    }
}