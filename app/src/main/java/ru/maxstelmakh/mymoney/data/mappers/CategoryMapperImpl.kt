package ru.maxstelmakh.mymoney.data.mappers

import ru.maxstelmakh.mymoney.data.models.CategoryModelData
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain
import ru.maxstelmakh.mymoney.domain.util.CategoryMapper
import javax.inject.Inject

class CategoryMapperImpl @Inject constructor(): CategoryMapper<CategoryModelData, CategoryModelDomain> {
    override suspend fun mapFromData(dataCategory: CategoryModelData): CategoryModelDomain {
        return CategoryModelDomain(
            category = dataCategory.category,
            color = dataCategory.color
        )
    }

    override fun mapToData(domainCategory: CategoryModelDomain): CategoryModelData {
        return CategoryModelData(
            category = domainCategory.category,
            color = domainCategory.color
        )
    }

    suspend fun mapFromDataCategoriesList(initial: List<CategoryModelData>): List<CategoryModelDomain> {
        return initial.map { mapFromData(it) }
    }

    suspend fun mapToDataCategoriesList(initial: List<CategoryModelDomain>): List<CategoryModelData> {
        return initial.map { mapToData(it) }
    }

}