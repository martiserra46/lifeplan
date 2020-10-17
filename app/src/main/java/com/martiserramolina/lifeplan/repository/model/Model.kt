package com.martiserramolina.lifeplan.repository.model

import com.martiserramolina.lifeplan.repository.enums.SituationDaySatisfaction
import com.martiserramolina.lifeplan.repository.enums.IdeaImportance
import java.util.*

data class YourLife(var text: String = "")

data class Topic(var name: String = "", var ideas: List<Idea> = emptyList())

data class Idea(
    var text: String = "",
    var importance: IdeaImportance = IdeaImportance.NORMAL,
    var lastTimeModified: Date = Date()
)

data class SituationDay(
    var day: Date = Date(),
    var text: String = "",
    var satisfaction: SituationDaySatisfaction = SituationDaySatisfaction.NORMAL,
)