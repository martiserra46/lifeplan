package com.martiserramolina.lifeplan.ui.fragments.instructions.sections

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.viewbinding.ViewBinding
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment

abstract class SectionInstructionsFragment<T : ViewBinding> : BaseFragment<T>() {

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.instructions_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.instructions_next_mi -> navigateToNextFragment().run { true }
            R.id.instructions_previous_mi -> navigateToPreviousFragment().run { true }
            else -> super.onOptionsItemSelected(item)
        }
    }

    abstract fun navigateToNextFragment()
    abstract fun navigateToPreviousFragment()
}