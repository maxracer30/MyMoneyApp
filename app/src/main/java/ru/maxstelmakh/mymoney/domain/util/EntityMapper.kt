package ru.maxstelmakh.mymoney.domain.util

interface EntityMapper <Entity, DomainModel>{
    suspend fun mapFromEntity(entity: Entity): DomainModel

    fun mapToEntity(domainModel: DomainModel): Entity
}