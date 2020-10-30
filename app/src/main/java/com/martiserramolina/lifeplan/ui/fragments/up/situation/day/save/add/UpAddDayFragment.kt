package com.martiserramolina.lifeplan.ui.fragments.up.situation.day.save.add

import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.ui.fragments.up.situation.day.save.UpSaveDayFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.situation.day.save.add.AddDayViewModel

class UpAddDayFragment : UpSaveDayFragment() {

    override val viewModel by ViewModelFactory.Delegate(
        this, AddDayViewModel::class.java
    ) { AddDayViewModel(mainActivity.application) }

    override fun getMenuResourceId(): Int = R.menu.situation_day_add_menu

    override fun getSaveMenuItemId(): Int = R.id.situation_day_add_save_mi

    override fun navigateToPreviousFragment() {
        mainActivity.navController.navigate(
            UpAddDayFragmentDirections.actionAddDayFragmentToMainFragment(NavSection.SITUATION)
        )
    }

    override fun getDaySavedMessage(): Int = R.string.day_added
}