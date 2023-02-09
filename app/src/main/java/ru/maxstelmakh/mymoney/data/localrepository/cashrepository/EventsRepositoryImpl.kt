package ru.maxstelmakh.mymoney.data.localrepository.cashrepository

import kotlinx.coroutines.flow.Flow
import ru.maxstelmakh.mymoney.data.models.EventMapper
import ru.maxstelmakh.mymoney.data.models.EventModelData
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import ru.maxstelmakh.mymoney.domain.repository.EventsRepositoryDao
import javax.inject.Inject


class EventsRepositoryImpl @Inject constructor(
    private val eventsRepositoryDao: EventsRepositoryDao,
    private val eventMapper: EventMapper
) : EventsRepository {

    private val _allFlowEvents: Flow<List<EventModelData>> = eventsRepositoryDao.getAllEvents()

    val allFlowEvents: Flow<List<EventModelData>> = eventsRepositoryDao.getAllEvents()

    override suspend fun getAllEvents(): Flow<List<EventModelDomain>> {
        return eventMapper.mapFromEntityList(eventsRepositoryDao.getAllEvents())
    }

    override suspend fun insertEvent(event: EventModelDomain) {
        eventsRepositoryDao.insertEvent(eventMapper.mapToEntity(event))
    }

    override suspend fun deleteEvent(event: EventModelDomain) {
        eventsRepositoryDao.deleteEvent(eventMapper.mapToEntity(event))
    }

    override suspend fun updateEvent(event: EventModelDomain) {
        eventsRepositoryDao.updateEvent(eventMapper.mapToEntity(event))
    }
}