package com.martiserramolina.lifeplan.ui.fragments.nav.your_life

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.martiserramolina.lifeplan.databinding.FragmentNavYourLifeBinding

class YourLifeFragment : Fragment() {

    private lateinit var binding: FragmentNavYourLifeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavYourLifeBinding.inflate(inflater, container, false)
        return binding.root
    }
}