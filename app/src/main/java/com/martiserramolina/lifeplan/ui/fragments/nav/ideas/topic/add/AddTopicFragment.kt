package com.martiserramolina.lifeplan.ui.fragments.nav.ideas.topic.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasTopicSaveBinding
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.repository.model.Topic
import com.martiserramolina.lifeplan.ui.activities.MainActivity
import com.martiserramolina.lifeplan.viewmodels.ideas.topic.add.AddTopicViewModel
import com.martiserramolina.lifeplan.viewmodels.your_life.edit.EditYourLifeViewModel

class AddTopicFragment : Fragment() {

    private lateinit var binding: FragmentNavIdeasTopicSaveBinding
    private val mainActivity by lazy { activity as MainActivity }
    private val viewModel by lazy { buildViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavIdeasTopicSaveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActionBar()
        setupSaveButton()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                navigateToMainFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun buildViewModel(): AddTopicViewModel {
        return ViewModelProvider(
            this, AddTopicViewModel.Factory(requireActivity().application)
        ).get(AddTopicViewModel::class.java)
    }

    private fun setupActionBar() {
        mainActivity.apply {
            setSupportActionBar(binding.fragmentNavIdeasTopicSaveTb)
            supportActionBar?.apply {
                title = getString(R.string.ideas_topic_add)
                setDisplayHomeAsUpEnabled(true)
            }
        }
        setHasOptionsMenu(true)
    }

    private fun setupSaveButton() {
        binding.fragmentNavIdeasTopicSaveSaveButtonB.setOnClickListener {
            viewModel.insertTopic(Topic(binding.fragmentNavIdeasTopicSaveTitleEt.text.toString()))
            navigateToMainFragment()
        }
    }

    private fun navigateToMainFragment() {
        mainActivity.navController.navigate(
            AddTopicFragmentDirections.actionAddTopicFragmentToMainFragment(NavSection.IDEAS)
        )
    }
}