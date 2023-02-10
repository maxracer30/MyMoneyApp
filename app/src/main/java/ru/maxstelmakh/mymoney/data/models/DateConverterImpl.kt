package ru.maxstelmakh.mymoney.data.models

import android.annotation.SuppressLint
import androidx.room.TypeConverter
import ru.maxstelmakh.mymoney.domain.util.DateConverter
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
class DateConverterImpl : DateConverter {

    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    @TypeConverter
    override fun toOffsetDateTime(value: String?): OffsetDateTime? {
        return value?.let {
            return formatter.parse(value, OffsetDateTime::from)
        }
    }

    @TypeConverter
    override fun fromOffsetDateTime(date: OffsetDateTime?): String? {
        return date?.format(formatter)
    }
}