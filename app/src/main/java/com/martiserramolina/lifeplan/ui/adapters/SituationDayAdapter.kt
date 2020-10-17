package com.martiserramolina.lifeplan.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.RviSituationDayBinding
import com.martiserramolina.lifeplan.extensions.format
import com.martiserramolina.lifeplan.repository.enums.SituationDaySatisfaction
import com.martiserramolina.lifeplan.repository.model.SituationDay
import java.util.*

class SituationDayAdapter : RecyclerView.Adapter<SituationDayAdapter.ViewHolder>() {

    private val listSituationDays = listOf(
        SituationDay(Date(), "afdasfasf fdsa fa", SituationDaySatisfaction.SATISFIED),
        SituationDay(Date(), "gggafdasfasf fdsa fa", SituationDaySatisfaction.NORMAL)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listSituationDays[position])
    }

    override fun getItemCount(): Int {
        return listSituationDays.size
    }

    class ViewHolder(
        private val binding: RviSituationDayBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                return ViewHolder(
                    RviSituationDayBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }
        }

        fun bind(situationDayItem: SituationDay) {
            binding.apply {
                rviSituationDayDateTv.text = situationDayItem.date.format("dd/mm/yyyy")
                rviSituationDayTextTv.text = situationDayItem.text
                rviSituationDayLeftCircleV.setBackgroundResource(
                    when (situationDayItem.satisfaction) {
                        SituationDaySatisfaction.SATISFIED ->
                            R.drawable.bg_rvi_situation_day_circle_satisfied
                        SituationDaySatisfaction.NORMAL ->
                            R.drawable.bg_rvi_situation_day_circle_normal
                        SituationDaySatisfaction.UNSATISFIED ->
                            R.drawable.bg_rvi_situation_day_circle_unsatisfied
                    }
                )
            }
        }
    }
}