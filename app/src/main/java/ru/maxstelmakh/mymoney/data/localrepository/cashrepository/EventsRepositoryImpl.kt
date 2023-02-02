package ru.maxstelmakh.mymoney.data.localrepository.cashrepository

import ru.maxstelmakh.mymoney.data.models.EventMapper
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import ru.maxstelmakh.mymoney.domain.repository.EventsRepositoryDao
import javax.inject.Inject


class EventsRepositoryImpl @Inject constructor(
    private val eventsRepositoryDao: EventsRepositoryDao,
    private val eventMapper: EventMapper
){

    suspend fun getAllEvents(): List<EventModelDomain> {
        return eventMapper.mapFromEntityList(eventsRepositoryDao.getAllEvents())
    }

    suspend fun insertEvent(event: EventModelDomain) {
        eventsRepositoryDao.insertEvent(eventMapper.mapToEntity(event))
    }

    suspend fun deleteEvent(event: EventModelDomain) {
        eventsRepositoryDao.deleteEvent(eventMapper.mapToEntity(event))
    }

    suspend fun updateEvent(event: EventModelDomain) {
        eventsRepositoryDao.updateEvent(eventMapper.mapToEntity(event))
    }
}