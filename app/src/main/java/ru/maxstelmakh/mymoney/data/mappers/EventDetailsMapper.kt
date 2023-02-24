package ru.maxstelmakh.mymoney.data.mappers

import ru.maxstelmakh.mymoney.data.models.EventInDetailModelData
import ru.maxstelmakh.mymoney.domain.model.EventInDetailModelDomain
import ru.maxstelmakh.mymoney.domain.util.EntityMapper
import javax.inject.Inject

class EventDetailsMapper @Inject constructor(
) : EntityMapper<EventInDetailModelData, EventInDetailModelDomain> {
    override suspend fun mapFromEntity(entity: EventInDetailModelData): EventInDetailModelDomain {
        return EventInDetailModelDomain(
            eventId = entity.eventId,
            expense = entity.expense,
            categoryName = entity.categoryName,
            color = entity.color,
            description = entity.description,
            categoryId = entity.categoryId,
            joined_date = entity.joined_date
        )
    }

    override fun mapToEntity(domainModel: EventInDetailModelDomain): EventInDetailModelData {
        return EventInDetailModelData(
            eventId = domainModel.eventId,
            expense = domainModel.expense,
            categoryName = domainModel.categoryName,
            color = domainModel.color,
            description = domainModel.description,
            categoryId = domainModel.categoryId,
            joined_date = domainModel.joined_date
        )
    }

    suspend fun mapFromEntityList(initial: List<EventInDetailModelData>): List<EventInDetailModelDomain> {
        return initial.map { mapFromEntity(it) }
    }
}