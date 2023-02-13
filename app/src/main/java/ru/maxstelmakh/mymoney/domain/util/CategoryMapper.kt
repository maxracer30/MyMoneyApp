package ru.maxstelmakh.mymoney.domain.util

interface CategoryMapper <DataCategory, DomainCategory>{
    suspend fun mapFromData(dataCategory: DataCategory): DomainCategory

    fun mapToData(domainCategory: DomainCategory): DataCategory
}