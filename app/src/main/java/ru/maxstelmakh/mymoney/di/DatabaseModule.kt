package ru.maxstelmakh.mymoney.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.maxstelmakh.mymoney.data.repository.cashrepository.EventsDatabase
import ru.maxstelmakh.mymoney.domain.repository.EventsRepositoryDao
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context): EventsDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = EventsDatabase::class.java,
            name = "events_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideEventsDao(eventsDatabase: EventsDatabase): EventsRepositoryDao {
        return eventsDatabase.eventsDao()
    }
}