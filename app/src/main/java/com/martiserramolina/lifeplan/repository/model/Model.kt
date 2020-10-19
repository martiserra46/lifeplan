package com.martiserramolina.lifeplan.repository.model

import android.os.Parcelable
import com.martiserramolina.lifeplan.repository.enums.SituationDaySatisfaction
import com.martiserramolina.lifeplan.repository.enums.IdeaImportance
import kotlinx.android.parcel.Parcelize
import java.util.*

data class YourLife(var text: String = "")

data class Topic(var name: String = "", var ideas: List<Idea> = emptyList())

data class Idea(
    var text: String = "",
    var importance: IdeaImportance = IdeaImportance.NORMAL,
    var lastTimeModified: Date = Date()
)

@Parcelize
data class SituationDay(
    var date: Date = Date(),
    var text: String = "",
    var satisfaction: SituationDaySatisfaction = SituationDaySatisfaction.NORMAL,
) : Parcelable