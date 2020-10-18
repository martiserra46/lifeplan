package com.martiserramolina.lifeplan.repository.enums

import android.content.Context
import com.martiserramolina.lifeplan.R

enum class IdeaImportance { IMPORTANT, NORMAL, UNIMPORTANT }

enum class SituationDaySatisfaction(val colorId: Int, val stringId: Int) {
    SATISFIED(R.color.colorSituationDayHighSatisfaction, R.string.high_satisfaction),
    NORMAL(R.color.colorSituationDayNormalSatisfaction, R.string.normal_satisfaction),
    UNSATISFIED(R.color.colorSituationDayLowSatisfaction, R.string.low_satisfaction);

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