package com.martiserramolina.lifeplan.ui.fragments.nav.ideas.topic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasTopicBinding
import com.martiserramolina.lifeplan.ui.fragments.FragmentWithBinding

class TopicFragment : FragmentWithBinding<ViewBinding>() {
    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        return FragmentNavIdeasTopicBinding.inflate(inflater, container, false)
    }
}