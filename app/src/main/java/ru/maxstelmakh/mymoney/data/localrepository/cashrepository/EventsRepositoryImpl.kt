package ru.maxstelmakh.mymoney.data.localrepository.cashrepository

import ru.maxstelmakh.mymoney.data.models.EventMapper
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import ru.maxstelmakh.mymoney.domain.repository.EventsRepositoryDao
import javax.inject.Inject


class EventsRepositoryImpl @Inject constructor(
    private val eventsRepositoryDao: EventsRepositoryDao,
    private val eventMapper: EventMapper
) : EventsRepository {

    override suspend fun getAllEvents(): List<EventModelDomain> {
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