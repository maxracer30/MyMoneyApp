package ru.maxstelmakh.mymoney.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

@Entity(
    foreignKeys = [ForeignKey(
        entity = CategoryModelData::class,
        parentColumns = arrayOf("categoryId"),
        childColumns = arrayOf("category"),
        onDelete = ForeignKey.SET_DEFAULT
    )]
)
data class EventModelData(
    @PrimaryKey(autoGenerate = true)
    var eventId: Long = 0,
    @ColumnInfo(defaultValue = "1")
    var category: Int,
    var expense: Long,
    var description: String,
    var joined_date: OffsetDateTime?
)
