package ru.maxstelmakh.mymoney.domain.model

import android.annotation.SuppressLint
import java.time.OffsetDateTime

@SuppressLint("NewApi")
data class EventModelDomain(
    var id: Long = 0,
    var expense: Int,
    var description: String,
    var category: String,
    var joined_date: OffsetDateTime? = OffsetDateTime.now()
)
