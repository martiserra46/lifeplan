package com.martiserramolina.lifeplan.ui.fragments.nav.situation.day.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import com.martiserramolina.lifeplan.databinding.FragmentNavSituationDaySaveBinding
import com.martiserramolina.lifeplan.ui.fragments.FragmentWithBinding

class EditSituationDayFragment : FragmentWithBinding<FragmentNavSituationDaySaveBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavSituationDaySaveBinding {
        return FragmentNavSituationDaySaveBinding.inflate(inflater, container, false)
    }

}