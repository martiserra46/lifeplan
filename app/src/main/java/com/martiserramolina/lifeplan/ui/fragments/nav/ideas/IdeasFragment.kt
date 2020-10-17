package com.martiserramolina.lifeplan.ui.fragments.nav.ideas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasBinding
import com.martiserramolina.lifeplan.ui.adapters.TopicAdapter
import com.martiserramolina.lifeplan.viewmodels.ideas.IdeasViewModel

class IdeasFragment : Fragment() {

    private lateinit var binding: FragmentNavIdeasBinding
    private val viewModel by lazy { buildViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavIdeasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentNavIdeasRv.apply {
            setHasFixedSize(true)
            adapter = TopicAdapter()
        }

    }

    private fun buildViewModel(): IdeasViewModel {
        return ViewModelProvider(
            this, IdeasViewModel.Factory(requireActivity().application)
        ).get(IdeasViewModel::class.java)
    }
}