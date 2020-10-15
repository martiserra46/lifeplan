package com.martiserramolina.lifeplan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasTopicBinding

class TopicFragment : Fragment() {

    private lateinit var binding: FragmentNavIdeasTopicBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavIdeasTopicBinding.inflate(inflater, container, false)
        return binding.root
    }
}