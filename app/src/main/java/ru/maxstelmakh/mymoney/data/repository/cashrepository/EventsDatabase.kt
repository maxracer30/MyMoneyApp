package ru.maxstelmakh.mymoney.data.repository.cashrepository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.maxstelmakh.mymoney.data.models.CategoryModelData
import ru.maxstelmakh.mymoney.data.models.EventModelData
import ru.maxstelmakh.mymoney.domain.repository.EventsRepositoryDao

@Database(
    entities = [
        EventModelData::class,
        CategoryModelData::class
    ], version = 1, exportSchema = false
)
@TypeConverters(DateConverterImpl::class)
abstract class EventsDatabase : RoomDatabase() {
    abstract fun eventsDao(): EventsRepositoryDao
}