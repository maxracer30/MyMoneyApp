package ru.maxstelmakh.mymoney.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

@Entity
data class EventModelData(
    @PrimaryKey(autoGenerate = true)
    var eventId: Long = 0,
    var expense: Int,
    var description: String,
    var category: String,
    var joined_date: OffsetDateTime?
    )
