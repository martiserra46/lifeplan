package com.martiserramolina.lifeplan.utils.enums

import com.martiserramolina.lifeplan.R

enum class NavSection(val labelId: Int, val destinationId: Int) {
    LIFE(R.string.life, R.id.lifeFragment),
    NOTES(R.string.notes, R.id.notesFragment),
    STATUS(R.string.status, R.id.statusFragment);

    companion object {
        fun getNavSection(destinationId: Int): NavSection {
            return values().first { it.destinationId == destinationId }
        }
    }
}