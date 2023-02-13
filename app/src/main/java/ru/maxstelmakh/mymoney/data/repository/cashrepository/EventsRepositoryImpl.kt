package ru.maxstelmakh.mymoney.data.repository.cashrepository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.maxstelmakh.mymoney.data.models.EventMapper
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import ru.maxstelmakh.mymoney.domain.repository.EventsRepositoryDao
import javax.inject.Inject


class EventsRepositoryImpl @Inject constructor(
    private val eventsRepositoryDao: EventsRepositoryDao,
    private val eventMapper: EventMapper
) : EventsRepository {

    override suspend fun getAllEvents(): Flow<List<EventModelDomain>> = flow {
        eventsRepositoryDao.getAllEvents().collect { listEventsModelData ->
            val listEventsModelDomain = eventMapper.mapFromEntityList(listEventsModelData)
            emit(listEventsModelDomain)
        }
    }

    override suspend fun insertEvent(event: EventModelDomain) {
        eventsRepositoryDao.insertEvent(eventMapper.mapToEntity(event))
    }

    override suspend fun deleteEvent(event: EventModelDomain) {
        eventsRepositoryDao.deleteEvent(eventMapper.mapToEntity(event))
    }

    override suspend fun updateEvent(event: EventModelDomain) {
        Log.d("StatesOfApp", "in repoImpl ${event.id.toString()}")
        eventsRepositoryDao.updateEvent(eventMapper.mapToEntity(event))
    }
}