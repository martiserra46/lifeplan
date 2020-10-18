package com.martiserramolina.lifeplan.ui.fragments.nav.situation.day

import android.view.LayoutInflater
import android.view.ViewGroup
import com.martiserramolina.lifeplan.databinding.FragmentNavSituationDayBinding
import com.martiserramolina.lifeplan.ui.fragments.FragmentWithBinding

class SituationDayFragment : FragmentWithBinding<FragmentNavSituationDayBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavSituationDayBinding {
        return FragmentNavSituationDayBinding.inflate(inflater, container, false)
    }

}