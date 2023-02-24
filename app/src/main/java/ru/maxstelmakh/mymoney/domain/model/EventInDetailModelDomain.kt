package ru.maxstelmakh.mymoney.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.OffsetDateTime

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class EventInDetailModelDomain(
    val eventId: Long,
    val expense: Long,
    val categoryName: String,
    val color: String,
    val description: String,
    val categoryId: Int,
    val joined_date: OffsetDateTime
): Parcelable
