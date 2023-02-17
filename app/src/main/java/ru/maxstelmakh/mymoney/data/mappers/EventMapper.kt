package ru.maxstelmakh.mymoney.data.mappers

import ru.maxstelmakh.mymoney.data.models.EventModelData
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import ru.maxstelmakh.mymoney.domain.util.EntityMapper
import javax.inject.Inject

class EventMapper @Inject constructor(): EntityMapper<EventModelData, EventModelDomain> {
    override suspend fun mapFromEntity(entity: EventModelData): EventModelDomain {
        return EventModelDomain(
            id = entity.eventId,
            expense = entity.expense,
            description = entity.description,
            joined_date = entity.joined_date
        )
    }

    override fun mapToEntity(domainModel: EventModelDomain): EventModelData {
        return EventModelData(
            eventId = domainModel.id,
            expense = domainModel.expense,
            description = domainModel.description,
            joined_date = domainModel.joined_date
        )
    }

    suspend fun mapFromEntityList(initial: List<EventModelData>): List<EventModelDomain> {
        return initial.map { mapFromEntity(it) }
    }

    suspend fun mapToEntityList(initial: List<EventModelDomain>): List<EventModelData> {
        return initial.map { mapToEntity(it) }
    }

}