package ru.maxstelmakh.mymoney.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.maxstelmakh.mymoney.data.repository.cashrepository.EventsRepositoryImpl
import ru.maxstelmakh.mymoney.domain.usecases.GetAllEventsUseCase
import ru.maxstelmakh.mymoney.domain.usecases.SaveNewEventUseCase

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetAllEventsUseCase(eventsRepositoryImpl: EventsRepositoryImpl): GetAllEventsUseCase {
        return GetAllEventsUseCase(eventsRepositoryImpl = eventsRepositoryImpl)
    }

    @Provides
    fun provideSaveNewEventUseCase(
        eventsRepositoryImpl: EventsRepositoryImpl
    ): SaveNewEventUseCase {
        return SaveNewEventUseCase(
            eventsRepositoryImpl = eventsRepositoryImpl
        )
    }


}