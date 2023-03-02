package ru.maxstelmakh.mymoney.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class CategoryModelDomain(
    var categoryId: Int = 0,
    var categoryName: String,
    var color: String,
    var image: Int
): Parcelable