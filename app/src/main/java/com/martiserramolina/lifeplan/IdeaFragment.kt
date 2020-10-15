package com.martiserramolina.lifeplan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasIdeaBinding

class IdeaFragment : Fragment() {

    private lateinit var binding: FragmentNavIdeasIdeaBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavIdeasIdeaBinding.inflate(inflater, container, false)
        return binding.root
    }
}