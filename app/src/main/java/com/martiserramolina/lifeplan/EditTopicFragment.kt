package com.martiserramolina.lifeplan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasTopicSaveBinding

class EditTopicFragment : Fragment() {

    private lateinit var binding: FragmentNavIdeasTopicSaveBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavIdeasTopicSaveBinding.inflate(inflater, container, false)
        return binding.root
    }
}