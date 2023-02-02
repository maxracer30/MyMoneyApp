package ru.maxstelmakh.mymoney.data.models

import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import ru.maxstelmakh.mymoney.domain.util.EntityMapper
import javax.inject.Inject

class EventMapper @Inject constructor(): EntityMapper<EventModelData, EventModelDomain> {
    override fun mapFromEntity(entity: EventModelData): EventModelDomain {
        return EventModelDomain(
            id = entity.primaryKey,
            title = entity.title,
            isExpense = entity.isExpense,
            description = entity.description,
            category = entity.category,
            account = entity.account
        )
    }

    override fun mapToEntity(domainModel: EventModelDomain): EventModelData {
        return EventModelData(
            primaryKey = domainModel.id,
            title = domainModel.title,
            isExpense = domainModel.isExpense,
            description = domainModel.description,
            category = domainModel.category,
            account = domainModel.account
        )
    }

    fun mapFromEntityList(initial: List<EventModelData>): List<EventModelDomain> {
        return initial.map { mapFromEntity(it) }
    }

    fun mapToEntityList(initial: List<EventModelDomain>): List<EventModelData> {
        return initial.map { mapToEntity(it) }
    }

}