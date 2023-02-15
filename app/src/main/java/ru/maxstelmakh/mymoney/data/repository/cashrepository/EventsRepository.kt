package ru.maxstelmakh.mymoney.data.repository.cashrepository

import kotlinx.coroutines.flow.Flow
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain

interface EventsRepository {

    //-------------------------- Events -----------------------------
    suspend fun insertEvent(event: EventModelDomain)

    suspend fun updateEvent(event: EventModelDomain)

    suspend fun deleteEvent(event: EventModelDomain)

    suspend fun getAllEvents(): Flow<List<EventModelDomain>>

    //------------------------ Categories --------------------------
    suspend fun getCategoryByName(category: String): CategoryModelDomain

    suspend fun insertCategory(category: CategoryModelDomain)

    suspend fun getAllCategories(): Flow<List<CategoryModelDomain>>
}
