package com.martiserramolina.lifeplan.ui.fragments.nav.your_life.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.martiserramolina.lifeplan.databinding.FragmentNavYourLifeSaveBinding

class EditYourLifeFragment : Fragment() {

    private lateinit var binding: FragmentNavYourLifeSaveBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavYourLifeSaveBinding.inflate(inflater, container, false)
        return binding.root
    }
}