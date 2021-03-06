package com.martiserramolina.lifeplan.ui.fragments.sections.nav.notes

import android.os.Bundle
import android.view.*
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavNotesBinding
import com.martiserramolina.lifeplan.repository.room.Notebook
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.notes.notebook.NotebookAdapter
import com.martiserramolina.lifeplan.ui.fragments.main.MainFragmentDirections
import com.martiserramolina.lifeplan.ui.fragments.sections.nav.NavFragment
import com.martiserramolina.lifeplan.utils.interfaces.LoadItemsFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.notes.info.InfoNotesViewModel

class NavNotesFragment : NavFragment<FragmentNavNotesBinding>(), LoadItemsFragment {

    private val viewModel by ViewModelFactory.Delegate(
        this, InfoNotesViewModel::class.java
    ) { InfoNotesViewModel(mainActivity.application) }

    override fun buildBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentNavNotesBinding = FragmentNavNotesBinding.inflate(
        inflater, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupItemsRecyclerView(
            binding.fragmentNavNotesRv,
            NotebookAdapter { navigateToNotebookFragment(it) },
            viewModel,
            viewLifecycleOwner,
            binding.fragmentNavNotesEmptyCl
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.notes_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.notes_notebook_add_mi -> onAddMenuItemSelected { navigateToAddNotebookFragment() }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToNotebookFragment(notebook: Notebook) {
        mainActivity.navController
            .navigate(MainFragmentDirections.actionMainFragmentToNotebookFragment(notebook))
    }

    private fun navigateToAddNotebookFragment() {
        mainActivity.navController
            .navigate(MainFragmentDirections.actionMainFragmentToAddNotebookFragment())
    }
}