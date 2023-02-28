package ru.maxstelmakh.mymoney.data.repository.cashrepository

import kotlinx.coroutines.flow.Flow
import ru.maxstelmakh.mymoney.data.relations.CategoriesWithEvents
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain
import ru.maxstelmakh.mymoney.domain.model.EventInDetailModelDomain
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import ru.maxstelmakh.mymoney.domain.model.StatisticModelDomain

interface EventsRepository {

    //-------------------------- Events -----------------------------
    suspend fun insertEvent(event: EventModelDomain)

    suspend fun updateEvent(event: EventModelDomain)

    suspend fun deleteEvent(event: EventModelDomain)

    suspend fun getAllEvents(): Flow<List<EventModelDomain>>

    suspend fun getAllEventsInDetail(): Flow<List<EventInDetailModelDomain>>

    //------------------------ Categories --------------------------
    suspend fun getCategoryByName(category: String): Flow<List<CategoriesWithEvents>>

    suspend fun insertCategory(category: CategoryModelDomain)

    suspend fun getAllCategories(): Flow<List<CategoryModelDomain>>

//    --------------------------- Statistics --------------------------

    suspend fun getCategoriesForStatistic(startPeriod: String, endPeriod: String): Flow<List<StatisticModelDomain>>
}
