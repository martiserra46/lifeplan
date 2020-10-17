package com.martiserramolina.lifeplan.ui.fragments.nav.your_life

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavYourLifeBinding
import com.martiserramolina.lifeplan.ui.activities.MainActivity
import com.martiserramolina.lifeplan.ui.fragments.MainFragmentDirections
import com.martiserramolina.lifeplan.viewmodels.your_life.YourLifeViewModel

class YourLifeFragment : Fragment() {

    private lateinit var binding: FragmentNavYourLifeBinding
    private val mainActivity by lazy { activity as MainActivity }
    private val viewModel by lazy { buildViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavYourLifeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupDescription()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.your_life_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.your_life_edit_mi -> {
                mainActivity.navController
                    .navigate(MainFragmentDirections.actionMainFragmentToEditYourLifeFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun buildViewModel(): YourLifeViewModel {
        return ViewModelProvider(
            this, YourLifeViewModel.Factory(requireActivity().application)
        ).get(YourLifeViewModel::class.java)
    }

    private fun setupDescription() {
        viewModel.yourLife.observe(viewLifecycleOwner) { yourLife ->
            yourLife?.let { binding.fragmentNavYourLifeDescriptionTv.text = yourLife.text }
        }
    }
}