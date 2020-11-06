package com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.day

import android.view.ViewGroup
import android.widget.Toast
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.ItemAdapter
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.day.DayDiffCallback
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.day.DayViewHolder

class DayAdapter(
    private val onItemClick: (Day) -> Unit
) : ItemAdapter<Day, DayViewHolder, DayDiffCallback>(DayDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder =
        DayViewHolder(parent, onItemClick)
}