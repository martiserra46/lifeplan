package com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.nav.situation.day.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavSituationDaySaveBinding
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.extensions.format
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

    override fun getTitleId(): Int = R.string.situation_day_add

    override fun navigateToPreviousFragment() {
        navController.navigate(
            AddSituationDayFragmentDirections
                .actionAddSituationDayFragmentToMainFragment(NavSection.SITUATION)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDate()
        setupSatisfactionSpinner()
    }

    private fun setupDate() {
        binding.fragmentNavSituationDaySaveDateTv.text = Date().format("dd/mm/yyyy")
    }

    private fun setupSatisfactionSpinner() {
        binding.fragmentNavSituationDaySaveSatisfactionSp.apply {
            adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.situationDaySatisfactions,
                android.R.layout.simple_spinner_item
            ).apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    view?.findViewById<TextView>(android.R.id.text1)?.apply {
                        val colorResource = when (text) {
                            getString(R.string.high_satisfaction) ->
                                R.color.colorSituationDayHighSatisfaction
                            getString(R.string.normal_satisfaction) ->
                                R.color.colorSituationDayNormalSatisfaction
                            else -> R.color.colorSituationDayLowSatisfaction
                        }
                        setTextColor(ContextCompat.getColor(context, colorResource))
                    }
                }

                override fun onNothingSelected(adapterView: AdapterView<*>?) {}
            }
        }
    }
}