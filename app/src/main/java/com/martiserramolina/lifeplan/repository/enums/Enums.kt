package com.martiserramolina.lifeplan.repository.enums

import android.content.Context
import com.martiserramolina.lifeplan.R

enum class IdeaImportance { IMPORTANT, NORMAL, UNIMPORTANT }

enum class SituationDaySatisfaction(val drawableId: Int, val colorId: Int, val stringId: Int) {
    SATISFIED(
        R.drawable.bg_rvi_situation_day_circle_satisfied,
        R.color.colorSituationDayHighSatisfaction,
        R.string.high_satisfaction
    ),
    NORMAL(
        R.drawable.bg_rvi_situation_day_circle_normal,
        R.color.colorSituationDayNormalSatisfaction,
        R.string.normal_satisfaction
    ),
    UNSATISFIED(
        R.drawable.bg_rvi_situation_day_circle_unsatisfied,
        R.color.colorSituationDayLowSatisfaction,
        R.string.low_satisfaction
    );

    companion object {
        fun getSituationDaySatisfactionByColorId(colorId: Int): SituationDaySatisfaction {
            return SituationDaySatisfaction.values().first { it.colorId == colorId }
        }

        fun getSituationDaySatisfactionByStringId(stringId: Int): SituationDaySatisfaction {
            return SituationDaySatisfaction.values().first { it.stringId == stringId }
        }

        fun getSituationDaySatisfactionByString(
            context: Context, string: String
        ): SituationDaySatisfaction {
            return SituationDaySatisfaction.values().first { context.getString(it.stringId) == string }
        }
    }
}