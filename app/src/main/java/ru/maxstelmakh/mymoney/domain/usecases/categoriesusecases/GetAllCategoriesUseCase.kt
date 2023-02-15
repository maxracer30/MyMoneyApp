package ru.maxstelmakh.mymoney.domain.usecases.categoriesusecases

import kotlinx.coroutines.flow.Flow
import ru.maxstelmakh.mymoney.data.repository.cashrepository.EventsRepository
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val eventsRepositoryImpl: EventsRepository
) {
    suspend operator fun invoke(): Flow<List<CategoryModelDomain>> {
        return eventsRepositoryImpl.getAllCategories()
    }
}