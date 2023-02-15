package ru.maxstelmakh.mymoney.domain.usecases.categoriesusecases

import ru.maxstelmakh.mymoney.data.repository.cashrepository.EventsRepository
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain
import javax.inject.Inject

class GetCategoryByNameUseCase @Inject constructor(
    private val eventsRepositoryImpl: EventsRepository
) {
    suspend operator fun invoke(category: String): CategoryModelDomain {
        return eventsRepositoryImpl.getCategoryByName(category)
    }
}