package ru.maxstelmakh.mymoney.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.maxstelmakh.mymoney.data.localrepository.cashrepository.EventsRepository
import ru.maxstelmakh.mymoney.data.localrepository.cashrepository.EventsRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
interface MyMoneyModule {

    @Binds
    fun bindsEventRepository(impl: EventsRepositoryImpl): EventsRepository
}