package ru.maxstelmakh.mymoney.domain.usecases.categoriesusecases

import kotlinx.coroutines.flow.Flow
import ru.maxstelmakh.mymoney.data.relations.CategoriesWithEvents
import ru.maxstelmakh.mymoney.data.repository.cashrepository.EventsRepository
import javax.inject.Inject

class GetCategoryByNameUseCase @Inject constructor(
    private val eventsRepositoryImpl: EventsRepository
) {
    suspend operator fun invoke(category: String): Flow<List<CategoriesWithEvents>> {
        return eventsRepositoryImpl.getCategoryByName(category)
    }
}