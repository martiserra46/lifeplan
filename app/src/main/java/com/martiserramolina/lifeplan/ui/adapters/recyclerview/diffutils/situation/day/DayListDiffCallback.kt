package com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.situation.day

import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.ItemListDiffCallback

class DayListDiffCallback(
    oldList: List<Day>,
    newList: List<Day>
) : ItemListDiffCallback<Day>(oldList, newList) {
    override fun getItemId(item: Day): Long = item.dayId
}