package ru.maxstelmakh.mymoney.domain.usecases.statisticusecases

import ru.maxstelmakh.mymoney.data.repository.cashrepository.EventsRepository
import ru.maxstelmakh.mymoney.domain.model.StatisticModelDomain
import javax.inject.Inject

class GetStatCategoriesUseCase @Inject constructor(
    private val eventsRepository: EventsRepository
){
    suspend operator fun invoke(startPeriod: String, endPeriod: String): List<StatisticModelDomain> {
        return  eventsRepository.getCategoriesForStatistic(startPeriod, endPeriod)
    }
}