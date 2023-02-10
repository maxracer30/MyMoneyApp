package ru.maxstelmakh.mymoney.domain.util

import java.time.OffsetDateTime

interface DateConverter {

    fun toOffsetDateTime(value: String?): OffsetDateTime?

    fun fromOffsetDateTime(date: OffsetDateTime?): String?
}