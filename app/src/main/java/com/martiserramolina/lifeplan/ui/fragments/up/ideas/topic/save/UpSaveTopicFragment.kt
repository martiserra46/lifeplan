package com.martiserramolina.lifeplan.ui.fragments.up.ideas.topic.save

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasTopicSaveBinding
import com.martiserramolina.lifeplan.ui.fragments.up.ideas.topic.UpTopicFragment
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.topic.save.SaveTopicViewModel

abstract class UpSaveTopicFragment : UpTopicFragment<FragmentNavIdeasTopicSaveBinding>() {

    protected abstract val viewModel: SaveTopicViewModel

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavIdeasTopicSaveBinding = FragmentNavIdeasTopicSaveBinding.inflate(
        inflater, container, false
    )

    override fun getToolbar(): Toolbar = binding.fragmentNavIdeasTopicSaveTb

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        whenTopicSavedNavigateToPreviousFragment()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(getMenuResource(), menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            getSaveMenuItemId() -> onSaveMenuItemSelected()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupViews() {
        setupTitleTextView()
    }

    private fun whenTopicSavedNavigateToPreviousFragment() {
        viewModel.topicSaved.observe(viewLifecycleOwner) {
            if (it) navigateToPreviousFragment()
        }
    }

    protected abstract fun getMenuResource(): Int

    protected abstract fun getSaveMenuItemId(): Int

    private fun onSaveMenuItemSelected(): Boolean = saveTopicIfValid().run { true }

    private fun setupTitleTextView() {
        binding.fragmentNavIdeasTopicSaveTitleEt.setText(viewModel.topic.topicText)
    }

    private fun saveTopicIfValid() {
        if (isTopicValid()) saveTopic() else showInvalidTopicMessage()
    }

    private fun isTopicValid(): Boolean = getTitleFromEditText().isNotEmpty()

    private fun saveTopic() {
        viewModel.topic.apply {
            topicText = getTitleFromEditText()
        }
        viewModel.saveTopic()
    }

    private fun showInvalidTopicMessage() {
        Toast.makeText(context, getString(R.string.invalid_topic), Toast.LENGTH_SHORT).show()
    }

    private fun getTitleFromEditText(): String {
        return binding.fragmentNavIdeasTopicSaveTitleEt.text.toString()
    }
}