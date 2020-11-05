package com.martiserramolina.lifeplan.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.martiserramolina.lifeplan.utils.functions.hideKeyboard
import com.martiserramolina.lifeplan.ui.activities.MainActivity

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    protected val mainActivity by lazy { activity as MainActivity }

    private var _binding: T? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = buildBinding(inflater, container)
        return binding.root
    }

    override fun onStop() {
        super.onStop()
        hideKeyboard()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun buildBinding(inflater: LayoutInflater, container: ViewGroup?): T

    private fun hideKeyboard() {
        requireActivity().hideKeyboard()
    }
}