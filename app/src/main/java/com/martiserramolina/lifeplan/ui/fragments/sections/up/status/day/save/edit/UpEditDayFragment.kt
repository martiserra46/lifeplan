package com.martiserramolina.lifeplan.ui.fragments.sections.up.status.day.save.edit

import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.ui.fragments.sections.up.status.day.save.UpSaveDayFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.status.day.save.edit.EditDayViewModel

class UpEditDayFragment : UpSaveDayFragment() {

    override val viewModel by ViewModelFactory.Delegate(
        this, EditDayViewModel::class.java
    ) {
        val args = UpEditDayFragmentArgs.fromBundle(requireArguments())
        EditDayViewModel(args.day, mainActivity.application)
    }

    override fun getMenuResourceId(): Int = R.menu.status_day_edit_menu

    override fun getSaveMenuItemId(): Int = R.id.status_day_edit_save_mi

    override fun navigateToPreviousFragment() {
        mainActivity.navController.navigate(
            UpEditDayFragmentDirections.actionEditDayFragmentToDayFragment(viewModel.day)
        )
    }

    override fun getDaySavedMessage(): Int = R.string.day_edited
}