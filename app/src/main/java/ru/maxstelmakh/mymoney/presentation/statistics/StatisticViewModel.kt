package ru.maxstelmakh.mymoney.presentation.statistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.maxstelmakh.mymoney.domain.model.StatisticModelDomain
import ru.maxstelmakh.mymoney.domain.usecases.statisticusecases.GetStatCategoriesUseCase
import javax.inject.Inject

@HiltViewModel
class StatisticViewModel @Inject constructor(
    private val getStatCategoriesUseCase: GetStatCategoriesUseCase
): ViewModel() {
    private val _statistic = MutableLiveData<List<StatisticModelDomain>>()
    val statistic: LiveData<List<StatisticModelDomain>> = _statistic

    init {
        viewModelScope.launch {
            getStatCategoriesUseCase().collect {
                _statistic.postValue(it)
            }
        }
    }
}