package com.martiserramolina.lifeplan.ui.fragments.nav.situation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martiserramolina.lifeplan.databinding.FragmentNavSituationBinding
import com.martiserramolina.lifeplan.ui.fragments.FragmentWithBinding
import com.martiserramolina.lifeplan.ui.adapters.SituationDayAdapter

class SituationFragment : FragmentWithBinding<FragmentNavSituationBinding>() {

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavSituationBinding {
        return FragmentNavSituationBinding.inflate(inflater, container, false)
    }

    override fun getRootView(): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentNavSituationRv.adapter = SituationDayAdapter()
    }

}