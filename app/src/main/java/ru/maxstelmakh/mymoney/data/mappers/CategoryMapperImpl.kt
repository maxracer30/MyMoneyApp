package ru.maxstelmakh.mymoney.data.mappers

import ru.maxstelmakh.mymoney.data.models.CategoryModelData
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain
import ru.maxstelmakh.mymoney.domain.util.EntityMapper
import javax.inject.Inject

class CategoryMapperImpl @Inject constructor(): EntityMapper<CategoryModelData, CategoryModelDomain> {
    override suspend fun mapFromEntity(entity: CategoryModelData): CategoryModelDomain {
        return CategoryModelDomain(
            categoryId = entity.categoryId,
            categoryName = entity.categoryName,
            color = entity.color,
            image = entity.image
        )
    }

    override fun mapToEntity(domainModel: CategoryModelDomain): CategoryModelData {
        return CategoryModelData(
            categoryId = domainModel.categoryId,
            categoryName = domainModel.categoryName,
            color = domainModel.color,
            image = domainModel.image
        )
    }

    suspend fun mapFromDataCategoriesList(initial: List<CategoryModelData>): List<CategoryModelDomain> {
        return initial.map { mapFromEntity(it) }
    }

//    Never used mapper
//    fun mapToDataCategoriesList(initial: List<CategoryModelDomain>): List<CategoryModelData> {
//        return initial.map { mapToData(it) }
//    }

}