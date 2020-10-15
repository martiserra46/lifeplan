package com.martiserramolina.lifeplan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasBinding

class IdeasFragment : Fragment() {

    private lateinit var binding: FragmentNavIdeasBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavIdeasBinding.inflate(inflater, container, false)
        return binding.root
    }
}