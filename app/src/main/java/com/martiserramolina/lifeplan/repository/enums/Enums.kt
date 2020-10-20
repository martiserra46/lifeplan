package com.martiserramolina.lifeplan.repository.enums

import com.martiserramolina.lifeplan.R

enum class IdeaImportance { IMPORTANT, NORMAL, UNIMPORTANT }

enum class DaySatisfaction(val drawableId: Int, val colorId: Int, val stringId: Int) {
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
    )
}