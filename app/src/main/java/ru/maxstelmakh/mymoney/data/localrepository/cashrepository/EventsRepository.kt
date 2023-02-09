package ru.maxstelmakh.mymoney.data.localrepository.cashrepository

import kotlinx.coroutines.flow.Flow
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain

interface EventsRepository {

    suspend fun getAllEvents(): Flow<List<EventModelDomain>>

    suspend fun insertEvent(event: EventModelDomain)

    suspend fun deleteEvent(event: EventModelDomain)

    suspend fun updateEvent(event: EventModelDomain)

}
