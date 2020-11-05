package com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.situation.day

import android.view.ViewGroup
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.ItemAdapter
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.situation.day.DayListDiffCallback
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.situation.day.DayViewHolder

class DayAdapter(
    onItemClick: (Day) -> Unit
) : ItemAdapter<DayViewHolder, DayListDiffCallback, Day>(onItemClick) {

    override fun buildViewHolder(
        parent: ViewGroup, viewType: Int
    ): DayViewHolder = DayViewHolder(parent, onItemClick)

    override fun buildItemListDiffCallback(
        oldList: List<Day>, newList: List<Day>
    ): DayListDiffCallback = DayListDiffCallback(oldList, newList)
}