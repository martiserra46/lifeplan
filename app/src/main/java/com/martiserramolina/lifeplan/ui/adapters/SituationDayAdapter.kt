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

    var listSituationDays = emptyList<SituationDay>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

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
                rviSituationDayCircleV
                    .setBackgroundResource(situationDayItem.satisfaction.drawableId)
            }
        }
    }
}