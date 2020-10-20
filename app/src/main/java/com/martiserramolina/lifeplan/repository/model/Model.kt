package com.martiserramolina.lifeplan.repository.model

import android.os.Parcelable
import com.martiserramolina.lifeplan.repository.enums.DaySatisfaction
import com.martiserramolina.lifeplan.repository.enums.IdeaImportance
import kotlinx.android.parcel.Parcelize
import java.util.*

data class Life(var text: String = "")

@Parcelize
data class Topic(var name: String = "", var ideas: List<Idea> = emptyList()) : Parcelable

@Parcelize
data class Idea(
    var text: String = "",
    var importance: IdeaImportance = IdeaImportance.NORMAL,
    var lastTimeModified: Date = Date()
) : Parcelable

@Parcelize
data class Day(
    var date: Date = Date(),
    var text: String = "",
    var satisfaction: DaySatisfaction = DaySatisfaction.NORMAL,
) : Parcelable