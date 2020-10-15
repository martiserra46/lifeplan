package com.martiserramolina.lifeplan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasIdeaSaveBinding

class AddIdeaFragment : Fragment() {

    private lateinit var binding: FragmentNavIdeasIdeaSaveBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavIdeasIdeaSaveBinding.inflate(inflater, container, false)
        return binding.root
    }
}