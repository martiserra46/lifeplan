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

enum class InstructionsSection(val titleId: Int, val descriptionId: Int) {
    LIFE(R.string.life_instructions_title, R.string.life_instructions_description),
    NOTES(R.string.notes_instructions_title, R.string.notes_instructions_description),
    STATUS(R.string.status_instructions_title, R.string.status_instructions_description)
}