package ru.maxstelmakh.mymoney.data.localrepository.cashrepository

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.maxstelmakh.mymoney.data.models.EventModelData
import ru.maxstelmakh.mymoney.domain.repository.EventsRepositoryDao

@Database(entities = [EventModelData::class], version = 1)
abstract class EventsDatabase : RoomDatabase() {
    abstract fun eventsDao(): EventsRepositoryDao
}