package com.martiserramolina.lifeplan.ui.fragments.nav.your_life.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavYourLifeSaveBinding
import com.martiserramolina.lifeplan.ui.activities.MainActivity

class EditYourLifeFragment : Fragment() {

    private lateinit var binding: FragmentNavYourLifeSaveBinding
    private val mainActivity by lazy { activity as MainActivity }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavYourLifeSaveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActionBar()
    }

    private fun setupActionBar() {
        mainActivity.apply {
            setSupportActionBar(binding.fragmentNavYourLifeSaveTb)
            supportActionBar?.apply {
                title = getString(R.string.your_life_edit)
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.ic_back)
            }
        }
        setHasOptionsMenu(true)
    }
}