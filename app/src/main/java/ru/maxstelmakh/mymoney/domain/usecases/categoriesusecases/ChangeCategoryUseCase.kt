package ru.maxstelmakh.mymoney.domain.usecases.categoriesusecases

import ru.maxstelmakh.mymoney.data.repository.cashrepository.EventsRepository
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain
import javax.inject.Inject

class ChangeCategoryUseCase @Inject constructor(
    private val repository: EventsRepository
) {
    suspend operator fun invoke(categoryModelDomain: CategoryModelDomain) {
        repository.updateCategory(category = categoryModelDomain)
    }
}
