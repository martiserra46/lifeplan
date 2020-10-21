package com.martiserramolina.lifeplan.repository.enums

import com.martiserramolina.lifeplan.R

enum class IdeaImportance(val drawableId: Int, val colorId: Int, val stringId: Int) {
    IMPORTANT(
        R.drawable.bg_rvi_ideas_idea_circle_important,
        R.color.colorIdeaHighImportance,
        R.string.high_importance
    ),
    NORMAL(
        R.drawable.bg_rvi_ideas_idea_circle_normal,
        R.color.colorIdeaNormalImportance,
        R.string.normal_importance
    ),
    UNIMPORTANT(
        R.drawable.bg_rvi_ideas_idea_circle_unimportant,
        R.color.colorIdeaLowImportance,
        R.string.low_importance
    )
}

enum class DaySatisfaction(val drawableId: Int, val colorId: Int, val stringId: Int) {
    SATISFIED(
        R.drawable.bg_rvi_situation_day_circle_satisfied,
        R.color.colorDayHighSatisfaction,
        R.string.high_satisfaction
    ),
    NORMAL(
        R.drawable.bg_rvi_situation_day_circle_normal,
        R.color.colorDayNormalSatisfaction,
        R.string.normal_satisfaction
    ),
    UNSATISFIED(
        R.drawable.bg_rvi_situation_day_circle_unsatisfied,
        R.color.colorDayLowSatisfaction,
        R.string.low_satisfaction
    )
}