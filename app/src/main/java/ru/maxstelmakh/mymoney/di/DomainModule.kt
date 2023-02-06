package ru.maxstelmakh.mymoney.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.maxstelmakh.mymoney.data.localrepository.cashrepository.EventsRepositoryImpl
import ru.maxstelmakh.mymoney.domain.repository.EventsRepositoryDao
import ru.maxstelmakh.mymoney.domain.usecases.GetAllEventsUseCase
import ru.maxstelmakh.mymoney.domain.usecases.SaveNewEventUseCase
import ru.maxstelmakh.mymoney.presentation.addevent.SaveEventParams

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetAllEventsUseCase(eventsRepositoryDao: EventsRepositoryDao): GetAllEventsUseCase {
        return GetAllEventsUseCase(eventsRepository = eventsRepositoryDao)
    }

    @Provides
    fun provideSaveNewEventUseCase(
        saveEventParams: SaveEventParams,
        eventsRepositoryImpl: EventsRepositoryImpl
    ): SaveNewEventUseCase {
        return SaveNewEventUseCase(
            saveEventParams = saveEventParams,
            eventsRepositoryImpl = eventsRepositoryImpl
        )
    }
}