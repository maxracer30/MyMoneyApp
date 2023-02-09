package ru.maxstelmakh.mymoney.data.models

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import ru.maxstelmakh.mymoney.domain.util.EntityMapper
import javax.inject.Inject

class EventMapper @Inject constructor(): EntityMapper<EventModelData, EventModelDomain> {
    override suspend fun mapFromEntity(entity: EventModelData): EventModelDomain {
        return EventModelDomain(
            id = entity.primaryKey,
            title = entity.title,
            expense = entity.expense,
            description = entity.description,
            category = entity.category,
        )
    }

    override fun mapToEntity(domainModel: EventModelDomain): EventModelData {
        return EventModelData(
            primaryKey = domainModel.id,
            title = domainModel.title,
            expense = domainModel.expense,
            description = domainModel.description,
            category = domainModel.category,
        )
    }

    suspend fun mapFromEntityList(initial: Flow<List<EventModelData>>): Flow<List<EventModelDomain>> {
        return initial.map { list -> list.map { mapFromEntity(it) } }
    }

    fun mapToEntityList(initial: List<EventModelDomain>): List<EventModelData> {
        return initial.map { mapToEntity(it) }
    }

}