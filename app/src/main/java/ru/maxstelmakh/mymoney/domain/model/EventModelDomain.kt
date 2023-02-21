@file:Suppress("DEPRECATED_ANNOTATION")

package ru.maxstelmakh.mymoney.domain.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.OffsetDateTime

@Parcelize
@SuppressLint("NewApi")
data class EventModelDomain(
    var id: Long = 0,
    var expense: Long,
    var description: String,
    var category: Int,
    var joined_date: OffsetDateTime? = OffsetDateTime.now()
) : Parcelable
