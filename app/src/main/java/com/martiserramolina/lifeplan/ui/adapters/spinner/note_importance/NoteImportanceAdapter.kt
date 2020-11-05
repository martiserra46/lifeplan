package com.martiserramolina.lifeplan.ui.adapters.spinner.note_importance

import android.content.Context
import com.martiserramolina.lifeplan.ui.adapters.spinner.SpinnerItemAdapter
import com.martiserramolina.lifeplan.repository.enums.NoteImportance

class NoteImportanceAdapter(
    context: Context
) : SpinnerItemAdapter<NoteImportance>(context, NoteImportance.values()) {
    override fun getStringId(value: NoteImportance): Int = value.stringId
    override fun getColorId(value: NoteImportance): Int = value.colorId
}