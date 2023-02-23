package ru.maxstelmakh.mymoney.data.mappers

import ru.maxstelmakh.mymoney.data.models.CategoryModelData
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain
import ru.maxstelmakh.mymoney.domain.util.CategoryMapper
import javax.inject.Inject

class CategoryMapperImpl @Inject constructor(): CategoryMapper<CategoryModelData, CategoryModelDomain> {
    override suspend fun mapFromData(dataCategory: CategoryModelData): CategoryModelDomain {
        return CategoryModelDomain(
            categoryId = dataCategory.categoryId,
            categoryName = dataCategory.categoryName,
            color = dataCategory.color,
            image = dataCategory.image
        )
    }

    override fun mapToData(domainCategory: CategoryModelDomain): CategoryModelData {
        return CategoryModelData(
            categoryId = domainCategory.categoryId,
            categoryName = domainCategory.categoryName,
            color = domainCategory.color,
            image = domainCategory.image
        )
    }

    suspend fun mapFromDataCategoriesList(initial: List<CategoryModelData>): List<CategoryModelDomain> {
        return initial.map { mapFromData(it) }
    }

//    Never used mapper
//    fun mapToDataCategoriesList(initial: List<CategoryModelDomain>): List<CategoryModelData> {
//        return initial.map { mapToData(it) }
//    }

}