package ru.maxstelmakh.mymoney.domain.usecases.categoriesusecases

import ru.maxstelmakh.mymoney.data.repository.cashrepository.EventsRepositoryImpl
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain
import javax.inject.Inject

class AddNewCategoryUseCase @Inject constructor(
    private val eventsRepositoryImpl: EventsRepositoryImpl
) {
    suspend operator fun invoke(categoryModelDomain: CategoryModelDomain) {
        eventsRepositoryImpl.insertCategory(
            CategoryModelDomain(
                category = categoryModelDomain.category,
                color = categoryModelDomain.color
            )
        )
    }
}