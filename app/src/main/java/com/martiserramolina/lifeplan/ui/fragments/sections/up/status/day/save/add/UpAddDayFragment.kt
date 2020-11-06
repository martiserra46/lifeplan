package com.martiserramolina.lifeplan.ui.fragments.sections.up.status.day.save.add

import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.utils.enums.NavSection
import com.martiserramolina.lifeplan.ui.fragments.sections.up.status.day.save.UpSaveDayFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.status.day.save.add.AddDayViewModel

class UpAddDayFragment : UpSaveDayFragment() {

    override val viewModel by ViewModelFactory.Delegate(
        this, AddDayViewModel::class.java
    ) { AddDayViewModel(mainActivity.application) }

    override fun getToolbarTitle(): String = getString(R.string.day_add_message)

    override fun getMenuResourceId(): Int = R.menu.status_day_add_menu

    override fun getSaveMenuItemId(): Int = R.id.status_day_add_save_mi

    override fun navigateToPreviousFragment() {
        mainActivity.navController.navigate(
            UpAddDayFragmentDirections.actionAddDayFragmentToMainFragment(NavSection.STATUS)
        )
    }

    override fun getDaySavedMessage(): Int = R.string.day_added
}