package com.martiserramolina.lifeplan.viewmodels.viewmodels.instructions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.martiserramolina.lifeplan.utils.enums.InstructionsSection

class InstructionsViewModel(
    instructionsSection: InstructionsSection,
    application: Application
) : AndroidViewModel(application) {

    val title: String
    val description: String
    
    init {
        val context = application.applicationContext
        title = context.getString(instructionsSection.titleId)
        description = context.getString(instructionsSection.descriptionId)
    }
}