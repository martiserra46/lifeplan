package com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.situation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.martiserramolina.lifeplan.databinding.RviSituationDayBinding
import com.martiserramolina.lifeplan.extensions.formatted
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.ItemViewHolder

class DayViewHolder(
    parent: ViewGroup,
    onItemClick: (Day) -> Unit
) : ItemViewHolder<RviSituationDayBinding, Day>(
    RviSituationDayBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
    ), onItemClick
) {
    override fun bindData(item: Day) {
        binding.apply {
            rviSituationDayDateTv.text = item.dayDate.formatted()
            rviSituationDayTextTv.text = item.dayText
            rviSituationDayCircleV.setBackgroundResource(item.daySatisfaction.drawableId)
        }
    }
}