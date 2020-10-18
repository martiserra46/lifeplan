package com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.nav.situation.day.add

import android.os.Bundle
import android.view.*
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
import com.martiserramolina.lifeplan.repository.enums.SituationDaySatisfaction
import com.martiserramolina.lifeplan.repository.model.SituationDay
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

    private fun saveSituationDay() {
        viewModel.description = binding.fragmentNavSituationDaySaveDescriptionEt.text.toString()
        viewModel.insertSituationDay()
        navigateToPreviousFragment()
    }

    private fun setupDate() {
        viewModel.date = Date()
        binding.fragmentNavSituationDaySaveDateTv.text = viewModel.date.format("dd/mm/yyyy")
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
                        viewModel.satisfaction = SituationDaySatisfaction
                            .getSituationDaySatisfactionByString(context, text.toString())
                        setTextColor(
                            ContextCompat.getColor(context, viewModel.satisfaction.colorId)
                        )
                    }
                }

                override fun onNothingSelected(adapterView: AdapterView<*>?) {}
            }
        }
    }
}