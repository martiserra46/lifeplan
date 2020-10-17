package com.martiserramolina.lifeplan.repository

import android.content.Context

class Repository(context: Context) {
    val yourLifeRepository by lazy { YourLifeRepository(context) }
    val ideasRepository by lazy { IdeasRepository(context) }
    val situationDayRepository by lazy { SituationDayRepository(context) }
}

class YourLifeRepository(context: Context) {

}

class IdeasRepository(context: Context) {

}

class SituationDayRepository(context: Context) {

}