package com.martiserramolina.lifeplan.ui.fragments.nav.your_life

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.databinding.FragmentNavYourLifeBinding
import com.martiserramolina.lifeplan.viewmodels.your_life.YourLifeViewModel

class YourLifeFragment : Fragment() {

    private lateinit var binding: FragmentNavYourLifeBinding
    private val viewModel by lazy { buildViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavYourLifeBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    private fun buildViewModel(): YourLifeViewModel {
        return ViewModelProvider(
            this,
            YourLifeViewModel.Factory(
                requireActivity().application)
        ).get(YourLifeViewModel::class.java)
    }
}