package com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.day

import android.view.LayoutInflater
import android.view.ViewGroup
import com.martiserramolina.lifeplan.databinding.RviStatusDayBinding
import com.martiserramolina.lifeplan.utils.functions.dayString
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.ItemViewHolder
import com.martiserramolina.lifeplan.utils.functions.timeString

class DayViewHolder(
    parent: ViewGroup,
    onItemClick: (Day) -> Unit
) : ItemViewHolder<RviStatusDayBinding, Day>(
    RviStatusDayBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
    ), onItemClick
) {
    override fun bindData(item: Day) {
        binding.apply {
            rviStatusDayDayTv.text = item.dayDate.dayString()
            rviStatusDayTimeTv.text = item.dayDate.timeString()
            rviStatusDayTextTv.text = item.dayText
            rviStatusDaySatisfactionV.setBackgroundResource(item.daySatisfaction.drawableId)
        }
    }
}