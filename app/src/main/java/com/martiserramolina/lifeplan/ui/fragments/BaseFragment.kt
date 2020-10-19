package com.martiserramolina.lifeplan.ui.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.martiserramolina.lifeplan.extensions.hideKeyboard

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    protected lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getBinding(inflater, container)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        hideKeyboard()
    }

    abstract fun getBinding(inflater: LayoutInflater, container: ViewGroup?): T

    private fun hideKeyboard() { requireActivity().hideKeyboard() }
}