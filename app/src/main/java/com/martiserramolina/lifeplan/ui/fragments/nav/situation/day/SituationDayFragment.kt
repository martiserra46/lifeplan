package com.martiserramolina.lifeplan.ui.fragments.nav.situation.day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.martiserramolina.lifeplan.databinding.FragmentNavSituationDayBinding

class SituationDayFragment : Fragment() {

    private lateinit var binding: FragmentNavSituationDayBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavSituationDayBinding.inflate(inflater, container, false)
        return binding.root
    }
}