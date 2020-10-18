package com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.nav.situation.day

import android.view.LayoutInflater
import android.view.ViewGroup
import com.martiserramolina.lifeplan.databinding.FragmentNavSituationDayBinding
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment

class SituationDayFragment : BaseFragment<FragmentNavSituationDayBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavSituationDayBinding {
        return FragmentNavSituationDayBinding.inflate(inflater, container, false)
    }

}