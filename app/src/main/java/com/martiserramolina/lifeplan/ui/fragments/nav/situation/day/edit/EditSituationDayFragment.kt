package com.martiserramolina.lifeplan.ui.fragments.nav.situation.day.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.martiserramolina.lifeplan.databinding.FragmentNavSituationDaySaveBinding

class EditSituationDayFragment : Fragment() {

    private lateinit var binding: FragmentNavSituationDaySaveBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavSituationDaySaveBinding.inflate(inflater, container, false)
        return binding.root
    }
}