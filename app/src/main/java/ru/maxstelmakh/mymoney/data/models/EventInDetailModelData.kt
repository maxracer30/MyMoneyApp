package ru.maxstelmakh.mymoney.data.models

import java.time.OffsetDateTime

data class EventInDetailModelData(
    val eventId: Long,
    val expense: Long,
    val categoryName: String,
    val color: String,
    val description: String,
    val categoryId: Int,
    val joined_date: OffsetDateTime
) {

}
