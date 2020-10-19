package com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.nav.situation.day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.databinding.FragmentNavSituationDayBinding
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.extensions.format
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment
import com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.SecondaryFragment
import com.martiserramolina.lifeplan.viewmodels.situation.day.SituationDayViewModel

class SituationDayFragment : SecondaryFragment<FragmentNavSituationDayBinding>() {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            SituationDayViewModel
                .Factory(SituationDayFragmentArgs.fromBundle(requireArguments()).situationDay)
        ).get(SituationDayViewModel::class.java) }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavSituationDayBinding {
        return FragmentNavSituationDayBinding.inflate(inflater, container, false)
    }

    override fun getToolbar(): Toolbar = binding.fragmentNavSituationDayTb

    override fun getToolbarTitle(): String = ""

    override fun navigateToPreviousFragment() {
        navController.navigate(
            SituationDayFragmentDirections
                .actionSituationDayFragmentToMainFragment(NavSection.SITUATION)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDateTextView()
        setupSatisfactionTextView()
        setupDescriptionTextView()
    }

    private fun setupDateTextView() {
        binding.fragmentNavSituationDayDateTv.text = viewModel.situationDay
            .date.format("dd/mm/yyyy")
    }

    private fun setupSatisfactionTextView() {
        viewModel.situationDay.satisfaction.let { satisfaction ->
            binding.fragmentNavSituationDaySatisfactionTv.apply {
                text = getString(satisfaction.stringId)
                setTextColor(ContextCompat.getColor(context, satisfaction.colorId))
            }
        }
    }

    private fun setupDescriptionTextView() {
        binding.fragmentNavSituationDayDescriptionTv.text = viewModel.situationDay.text
    }
}