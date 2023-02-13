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
    var expense: Int,
    var description: String,
    var category: String,
    var joined_date: OffsetDateTime? = OffsetDateTime.now()
) : Parcelable
