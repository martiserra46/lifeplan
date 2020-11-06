package com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.day

import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.ItemDiffCallback

class DayDiffCallback : ItemDiffCallback<Day>() {
    override fun areItemsTheSame(oldItem: Day, newItem: Day): Boolean =
        oldItem.dayId == newItem.dayId
    override fun areContentsTheSame(oldItem: Day, newItem: Day): Boolean =
        oldItem == newItem
}