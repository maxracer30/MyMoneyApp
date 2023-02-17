package ru.maxstelmakh.mymoney.data.relations

import androidx.room.Entity

@Entity(primaryKeys = ["categoryId", "eventId"])
data class CategoryEventCrossRef(
    val categoryId: Long,
    val eventId: Long
)
