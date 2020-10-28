package com.martiserramolina.lifeplan.ui.fragments.up.ideas.idea.info

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasIdeaBinding
import com.martiserramolina.lifeplan.ui.fragments.up.UpFragment
import com.martiserramolina.lifeplan.ui.fragments.up.ideas.idea.IdeaFragmentArgs
import com.martiserramolina.lifeplan.ui.fragments.up.ideas.idea.IdeaFragmentDirections
import com.martiserramolina.lifeplan.viewmodels.viewmodels.ideas.idea.info.InfoIdeaViewModel

class UpInfoIdeaFragment : UpFragment<FragmentNavIdeasIdeaBinding>() {

    private val viewModel by lazy {
        val args = IdeaFragmentArgs.fromBundle(requireArguments())
        ViewModelProvider(
            this,
            InfoIdeaViewModel.Factory(args.topic, args.idea, requireActivity().application)
        ).get(InfoIdeaViewModel::class.java)
    }

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavIdeasIdeaBinding {
        return FragmentNavIdeasIdeaBinding.inflate(inflater, container, false)
    }

    override fun getToolbar(): Toolbar = binding.fragmentNavIdeasIdeaTb

    override fun getToolbarTitle(): String = ""

    override fun navigateToPreviousFragment() {
        mainActivityNavController
            .navigate(IdeaFragmentDirections.actionIdeaFragmentToTopicFragment(viewModel.topic))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTitleTv()
        setupImportanceTv()
        setupDescriptionTv()
        navigateToPreviousFragmentAfterDbOp()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.ideas_idea_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.ideas_idea_edit_mi -> {
                navigateToEditIdeaFragment()
                true
            }
            R.id.ideas_idea_delete_mi -> {
                deleteIdea()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupTitleTv() {
        binding.fragmentNavIdeasIdeaTitleTv.text = viewModel.idea.ideaTitle
    }

    private fun setupImportanceTv() {
        viewModel.idea.ideaImportance.let { importance ->
            binding.fragmentNavIdeasIdeaImportanceTv.apply {
                text = getString(importance.stringId)
                setTextColor(ContextCompat.getColor(context, importance.colorId))
            }
        }
    }

    private fun setupDescriptionTv() {
        binding.fragmentNavIdeasIdeaDescriptionTv.text = viewModel.idea.ideaDescription
    }

    private fun navigateToEditIdeaFragment() {
        mainActivityNavController.navigate(
            IdeaFragmentDirections.actionIdeaFragmentToEditIdeaFragment(
                viewModel.idea,
                viewModel.topic
            )
        )
    }

    private fun deleteIdea() {
        viewModel.deleteIdea()
    }

    private fun navigateToPreviousFragmentAfterDbOp() {
        viewModel.ideaDeleted.observe(viewLifecycleOwner) { ideaDeleted ->
            if (ideaDeleted) navigateToPreviousFragment()
        }
    }
}