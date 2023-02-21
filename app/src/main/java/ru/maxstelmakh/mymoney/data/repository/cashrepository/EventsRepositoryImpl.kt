package ru.maxstelmakh.mymoney.data.repository.cashrepository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.maxstelmakh.mymoney.data.mappers.CategoryMapperImpl
import ru.maxstelmakh.mymoney.data.mappers.EventMapper
import ru.maxstelmakh.mymoney.data.relations.CategoriesWithEvents
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import ru.maxstelmakh.mymoney.domain.repository.EventsRepositoryDao
import javax.inject.Inject


class EventsRepositoryImpl @Inject constructor(
    private val eventsRepositoryDao: EventsRepositoryDao,
    private val eventMapper: EventMapper,
    private val categoryMapper: CategoryMapperImpl
) : EventsRepository {

    //-------------------------- Events -----------------------------
    override suspend fun getAllEvents(): Flow<List<EventModelDomain>> = flow {
        eventsRepositoryDao.getAllEvents().collect { listEventsModelData ->
            val listEventsModelDomain = eventMapper.mapFromEntityList(listEventsModelData)
            emit(listEventsModelDomain)
        }
    }

    override suspend fun insertEvent(event: EventModelDomain) {
        eventsRepositoryDao.insertEvent(eventMapper.mapToEntity(event))
//        val id = eventsRepositoryDao.getIdLastEvent()
//        Log.d("StatesOfApp", "in repoImpl insert $id")
//        eventsRepositoryDao.insertRef(CategoryEventCrossRef(0, eventId = id))
    }

    override suspend fun deleteEvent(event: EventModelDomain) {
        eventsRepositoryDao.deleteEvent(eventMapper.mapToEntity(event))
    }

    override suspend fun updateEvent(event: EventModelDomain) {
        Log.d("StatesOfApp", "in repoImpl ${event.id}")
        eventsRepositoryDao.updateEvent(eventMapper.mapToEntity(event))
    }

    //-------------------------- Categories -----------------------------
    override suspend fun getAllCategories(): Flow<List<CategoryModelDomain>> = flow {
        eventsRepositoryDao.getAllCategories().collect { listCategoriesModelData ->
            val listEventsModelDomain =
                categoryMapper
                    .mapFromDataCategoriesList(listCategoriesModelData)
            emit(listEventsModelDomain)
        }
    }

    override suspend fun insertCategory(category: CategoryModelDomain) {
        eventsRepositoryDao.insertCategory(categoryMapper.mapToData(category))
    }

    override suspend fun getCategoryByName(category: String): Flow<List<CategoriesWithEvents>> =
        eventsRepositoryDao.getCategoryOfEvents(category)

}
