package com.martiserramolina.lifeplan.ui.fragments.nav.ideas.idea.add

import android.view.LayoutInflater
import android.view.ViewGroup
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasIdeaSaveBinding
import com.martiserramolina.lifeplan.ui.fragments.FragmentWithBinding

class AddIdeaFragment : FragmentWithBinding<FragmentNavIdeasIdeaSaveBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavIdeasIdeaSaveBinding {
        return FragmentNavIdeasIdeaSaveBinding.inflate(inflater, container, false)
    }
}