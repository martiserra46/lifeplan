package com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.nav.situation.day

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavSituationDayBinding
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.extensions.format
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment
import com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.SecondaryFragment
import com.martiserramolina.lifeplan.viewmodels.situation.day.SituationDayViewModel

class SituationDayFragment : SecondaryFragment<FragmentNavSituationDayBinding>() {

    private val viewModel by lazy {
        val situationDayFragmentArgs = SituationDayFragmentArgs.fromBundle(requireArguments())
        ViewModelProvider(
            this,
            SituationDayViewModel
                .Factory(
                    situationDayFragmentArgs.situationDayId,
                    situationDayFragmentArgs.situationDay
                )
        ).get(SituationDayViewModel::class.java)
    }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.situation_day_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.situation_day_edit_mi -> {
                navigateToEditSituationDayFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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

    private fun navigateToEditSituationDayFragment() {
        navController.navigate(
            SituationDayFragmentDirections
                .actionSituationDayFragmentToEditSituationDayFragment(viewModel.situationDayId, viewModel.situationDay)
        )
    }
}