package com.martiserramolina.lifeplan.enums

import com.martiserramolina.lifeplan.R

enum class NavSection(val labelId: Int, val destinationId: Int) {
    LIFE(R.string.life, R.id.lifeFragment),
    IDEAS(R.string.ideas, R.id.ideasFragment),
    SITUATION(R.string.situation, R.id.situationFragment);

    companion object {
        fun getNavSection(destinationId: Int): NavSection {
            return values().first { it.destinationId == destinationId }
        }
    }
}