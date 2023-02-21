package ru.maxstelmakh.mymoney.data.mappers

import android.annotation.SuppressLint
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
            joined_date = entity.joined_date,
            category = entity.category
        )
    }

    @SuppressLint("NewApi")
    override fun mapToEntity(domainModel: EventModelDomain): EventModelData {
        return EventModelData(
            eventId = domainModel.id,
            expense = domainModel.expense,
            description = domainModel.description,
            joined_date = domainModel.joined_date,
            category = domainModel.category
        )
    }

    suspend fun mapFromEntityList(initial: List<EventModelData>): List<EventModelDomain> {
        return initial.map { mapFromEntity(it) }
    }

//    NeverUsedMapper
//    suspend fun mapToEntityList(initial: List<EventModelDomain>): List<EventModelData> {
//        return initial.map { mapToEntity(it) }
//    }

}