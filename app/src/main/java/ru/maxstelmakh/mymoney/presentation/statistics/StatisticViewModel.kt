package ru.maxstelmakh.mymoney.presentation.statistics

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.maxstelmakh.mymoney.domain.model.StatisticModelDomain
import ru.maxstelmakh.mymoney.domain.usecases.statisticusecases.GetStatCategoriesUseCase
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.format.TextStyle
import java.util.*
import javax.inject.Inject

@SuppressLint("NewApi")
@HiltViewModel
class StatisticViewModel @Inject constructor(
    private val getStatCategoriesUseCase: GetStatCategoriesUseCase
) : ViewModel() {
    private val _statistic = MutableLiveData<List<StatisticModelDomain>>()
    val statistic: LiveData<List<StatisticModelDomain>> = _statistic


    var startPeriod = LocalDate.now()
    var endPeriod = LocalDate.now()


    init {
        viewModelScope.launch {
            getStatCategoriesUseCase().collect {
                _statistic.postValue(it)
            }
        }
        getDatesOfWeek()
    }

    fun getDatesOfWeek() {
        // Go backward to get Monday
        while (startPeriod.dayOfWeek != DayOfWeek.MONDAY) {
            startPeriod = startPeriod.minusDays(1)
        }

        // Go forward to get Sunday
        while (endPeriod.dayOfWeek != DayOfWeek.SUNDAY) {
            endPeriod = endPeriod.plusDays(1)
        }
    }

    val dateInfo: String
        get() {
            getDatesOfWeek()
            return StringBuilder()
                .append(startPeriod.dayOfMonth)
                .append(" ")
                .append(monthToRu(startPeriod))
                .append(" - ")
                .append(endPeriod.dayOfMonth)
                .append(" ")
                .append(monthToRu(endPeriod))
                .toString()
        }

    @SuppressLint("NewApi")
    private fun monthToRu(date: LocalDate): String {
        return Month
            .of(date.monthValue)
            .getDisplayName(TextStyle.FULL_STANDALONE, Locale("ru"))
    }

    fun minusWeek() {
        startPeriod = startPeriod.minusWeeks(1)
        endPeriod = endPeriod.minusWeeks(1)
    }

    fun plusWeek() {
        startPeriod = startPeriod.plusWeeks(1)
        endPeriod = endPeriod.plusWeeks(1)
    }
}