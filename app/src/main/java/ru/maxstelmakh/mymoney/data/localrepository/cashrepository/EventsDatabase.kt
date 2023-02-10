package ru.maxstelmakh.mymoney.data.localrepository.cashrepository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.maxstelmakh.mymoney.data.models.DateConverterImpl
import ru.maxstelmakh.mymoney.data.models.EventModelData
import ru.maxstelmakh.mymoney.domain.repository.EventsRepositoryDao

@Database(entities = [EventModelData::class], version = 1, exportSchema = false)
@TypeConverters(DateConverterImpl::class)
abstract class EventsDatabase : RoomDatabase() {
    abstract fun eventsDao(): EventsRepositoryDao
}