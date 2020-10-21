package com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.nav.situation.day.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavSituationDaySaveBinding
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
}