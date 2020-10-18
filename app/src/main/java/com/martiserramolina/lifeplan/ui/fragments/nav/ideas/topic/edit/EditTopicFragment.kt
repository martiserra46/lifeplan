package com.martiserramolina.lifeplan.ui.fragments.nav.ideas.topic.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasTopicSaveBinding
import com.martiserramolina.lifeplan.ui.fragments.FragmentWithBinding

class EditTopicFragment : FragmentWithBinding<FragmentNavIdeasTopicSaveBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavIdeasTopicSaveBinding {
        return FragmentNavIdeasTopicSaveBinding.inflate(inflater, container, false)
    }
}