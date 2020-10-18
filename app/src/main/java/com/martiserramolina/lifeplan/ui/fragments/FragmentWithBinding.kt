package com.martiserramolina.lifeplan.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class FragmentWithBinding<T : Any> : Fragment() {

    protected lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getBinding(inflater, container)
        return getRootView()
    }

    abstract fun getBinding(inflater: LayoutInflater, container: ViewGroup?): T

    abstract fun getRootView(): View
}