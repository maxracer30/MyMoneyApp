package ru.maxstelmakh.mymoney.data.mappers

import ru.maxstelmakh.mymoney.data.models.StatisticModelData
import ru.maxstelmakh.mymoney.domain.model.StatisticModelDomain
import ru.maxstelmakh.mymoney.domain.util.EntityMapper
import javax.inject.Inject

class StatisticMapper @Inject constructor(
) : EntityMapper<StatisticModelData, StatisticModelDomain> {
    override suspend fun mapFromEntity(entity: StatisticModelData): StatisticModelDomain {
        return StatisticModelDomain(
            categoryId = entity.categoryId,
            categoryName = entity.categoryName,
            color = entity.color,
            image = entity.image,
            total = entity.total,
            percent = entity.percent
        )
    }

    override fun mapToEntity(domainModel: StatisticModelDomain): StatisticModelData {
        return StatisticModelData(
            categoryId = domainModel.categoryId,
            categoryName = domainModel.categoryName,
            color = domainModel.color,
            image = domainModel.image,
            total = domainModel.total,
            percent = domainModel.percent
        )
    }

    suspend fun mapFromDataStatisticList(initial: List<StatisticModelData>): List<StatisticModelDomain> {
        return initial.map { mapFromEntity(it) }
    }
}