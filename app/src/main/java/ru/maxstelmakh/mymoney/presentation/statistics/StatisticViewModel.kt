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
        getDatesOfWeek()

        val start = startPeriod.toString()
        val end = startPeriod.toString()

        viewModelScope.launch {
            getStatCategoriesUseCase(start, end).collect {
                _statistic.postValue(it)
            }
        }
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

    fun dateInfo(period: String): String {
        var result = ""
        when (period) {
            "day" -> {}
            "week" -> {
                getDatesOfWeek()
                result = StringBuilder()
                    .append(startPeriod.dayOfMonth)
                    .append(" ")
                    .append(monthToRu(startPeriod))
                    .append(" - ")
                    .append(endPeriod.dayOfMonth)
                    .append(" ")
                    .append(monthToRu(endPeriod))
                    .toString()
            }
            "month" -> {}
            "year" -> {}
            "period" -> {}
        }
        return result
    }

    @SuppressLint("NewApi")
    private fun monthToRu(date: LocalDate): String {
        return Month
            .of(date.monthValue)
            .getDisplayName(TextStyle.FULL_STANDALONE, Locale("ru"))
    }

    fun changeWeek(weeks: Long): String {
        startPeriod = startPeriod.plusWeeks(weeks)
        endPeriod = endPeriod.plusWeeks(weeks)
        return dateInfo("week")
    }

    fun changeDay(day: Long): String {
        startPeriod = startPeriod.plusDays(day)
        endPeriod = endPeriod.plusDays(day)
        return dateInfo("week")
    }
}