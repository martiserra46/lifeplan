package com.martiserramolina.lifeplan.repository.enums

import com.martiserramolina.lifeplan.R

enum class NoteImportance(val drawableId: Int, val colorId: Int, val stringId: Int) {
    IMPORTANT(
        R.drawable.bg_note_high_importance,
        R.color.colorNoteHighImportance,
        R.string.high_importance
    ),
    NORMAL(
        R.drawable.bg_note_medium_importance,
        R.color.colorNoteMediumImportance,
        R.string.medium_importance
    ),
    UNIMPORTANT(
        R.drawable.bg_note_low_importance,
        R.color.colorNoteLowImportance,
        R.string.low_importance
    )
}

enum class DaySatisfaction(val drawableId: Int, val colorId: Int, val stringId: Int) {
    SATISFIED(
        R.drawable.bg_day_high_satisfaction,
        R.color.colorDayHighSatisfaction,
        R.string.high_satisfaction
    ),
    NORMAL(
        R.drawable.bg_day_medium_satisfaction,
        R.color.colorDayMediumSatisfaction,
        R.string.medium_satisfaction
    ),
    UNSATISFIED(
        R.drawable.bg_day_low_satisfaction,
        R.color.colorDayLowSatisfaction,
        R.string.low_satisfaction
    )
}