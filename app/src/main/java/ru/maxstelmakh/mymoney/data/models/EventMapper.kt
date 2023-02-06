package ru.maxstelmakh.mymoney.data.models

import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import ru.maxstelmakh.mymoney.domain.util.EntityMapper
import javax.inject.Inject

class EventMapper @Inject constructor(): EntityMapper<EventModelData, EventModelDomain> {
    override fun mapFromEntity(entity: EventModelData): EventModelDomain {
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

    fun mapFromEntityList(initial: List<EventModelData>): List<EventModelDomain> {
        return initial.map { mapFromEntity(it) }
    }

    fun mapToEntityList(initial: List<EventModelDomain>): List<EventModelData> {
        return initial.map { mapToEntity(it) }
    }

}