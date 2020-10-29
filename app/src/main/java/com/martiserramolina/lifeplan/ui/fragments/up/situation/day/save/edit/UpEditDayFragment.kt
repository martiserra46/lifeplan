package com.martiserramolina.lifeplan.ui.fragments.up.situation.day.save.edit

import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.ui.fragments.up.situation.day.save.UpSaveDayFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.situation.day.save.edit.EditDayViewModel

class UpEditDayFragment : UpSaveDayFragment() {

    override val viewModel by ViewModelFactory.Delegate(
        this, EditDayViewModel::class.java
    ) {
        val args = EditDayFragmentArgs.fromBundle(requireArguments())
        EditDayViewModel(args.day, mainActivity.application)
    }

    override fun getMenuResourceId(): Int = R.menu.situation_day_edit_menu

    override fun getSaveMenuItemId(): Int = R.id.situation_day_edit_save_mi

    override fun navigateToPreviousFragment() {
        mainActivity.navController.navigate(
            EditDayFragmentDirections.actionEditDayFragmentToDayFragment(viewModel.day)
        )
    }
}