package ru.maxstelmakh.mymoney.presentation.statistics

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.maxstelmakh.mymoney.domain.model.StatisticModelDomain
import ru.maxstelmakh.mymoney.domain.usecases.statisticusecases.GetStatCategoriesUseCase
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.format.TextStyle
import java.util.*
import javax.inject.Inject

@SuppressLint("NewApi", "NullSafeMutableLiveData")
@HiltViewModel
class StatisticViewModel @Inject constructor(
    private val getStatCategoriesUseCase: GetStatCategoriesUseCase
) : ViewModel() {
    private val _statistic = MutableLiveData<List<StatisticModelDomain>>()
    val statistic: LiveData<List<StatisticModelDomain>> = _statistic


    private var startPeriod: LocalDate = LocalDate.now()
    private var endPeriod: LocalDate = LocalDate.now()


    fun startStatistic() {
        getDatesOfWeek()
        getData()

    }

    fun updateData() {
        getData()
    }
    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            _statistic.postValue(getStatCategoriesUseCase(startPeriod.toString(), endPeriod.toString()))
        }
    }

    fun dateInfo(period: String): String {
        return when (period) {
            "day" -> {
                return when (startPeriod) {
                    LocalDate.now() -> {
                        StringBuilder()
                            .append("Сегодня")
                            .toString()
                    }
                    LocalDate.now().minusDays(1) -> {
                        StringBuilder()
                            .append("Вчера")
                            .toString()
                    }
                    else -> {
                        StringBuilder()
                            .append(startPeriod.dayOfMonth)
                            .append(" ")
                            .append(monthToRu(startPeriod, 1))
                            .append(" ")
                            .append(startPeriod.year)
                            .toString()
                    }
                }
            }
            "week" -> {
                StringBuilder()
                    .append(startPeriod.dayOfMonth)
                    .append(" ")
                    .append(monthToRu(startPeriod, 1))
                    .append(" - ")
                    .append(endPeriod.dayOfMonth)
                    .append(" ")
                    .append(monthToRu(endPeriod, 1))
                    .toString()
            }
            "month" -> {
                StringBuilder()
                    .append(monthToRu(startPeriod, 0))
                    .append(" ")
                    .append(startPeriod.year)
                    .toString()
            }
            "year" -> {
                StringBuilder()
                    .append(startPeriod.year)
                    .toString()
            }
            "period" -> {
                return when (startPeriod.year) {
                    endPeriod.year -> {
                        StringBuilder()
                            .append(startPeriod.dayOfMonth)
                            .append(" ")
                            .append(monthToRu(startPeriod, 1))
                            .append(" - ")
                            .append(endPeriod.dayOfMonth)
                            .append(" ")
                            .append(monthToRu(endPeriod, 1))
                            .toString()
                    }
                    else -> {
                        StringBuilder()
                            .append(startPeriod.dayOfMonth)
                            .append(" ")
                            .append(monthToRu(startPeriod, 1))
                            .append(" ")
                            .append(startPeriod.year)
                            .append(" - \n")
                            .append(endPeriod.dayOfMonth)
                            .append(" ")
                            .append(monthToRu(endPeriod, 1))
                            .append(" ")
                            .append(endPeriod.year)
                            .toString()
                    }
                }
            }
            else -> {"Nothing"}
        }
    }

    // Returns months name in 0 -> nominative case, 1 -> genitive case, else -> Int
    @SuppressLint("NewApi")
    private fun monthToRu(date: LocalDate, case: Int = -1): String {
        return when (case) {
            0 -> {
                var month = Month
                    .of(date.monthValue)
                    .getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault())
                month = month.substring(0, 1).uppercase(Locale.getDefault()) + month.substring(1)
                month.toString()
            }
            1 -> {
                Month
                    .of(date.monthValue)
                    .getDisplayName(TextStyle.FULL, Locale.getDefault())
            }
            else -> {
                date.monthValue.toString()
            }
        }
    }

    fun setNowDate() {
        startPeriod = LocalDate.now()
        endPeriod = LocalDate.now()
    }

    fun changeDay(day: Long): String {
        startPeriod = startPeriod.plusDays(day)
        endPeriod = startPeriod.plusDays(1)
        getData()
        return dateInfo("day")
    }

    fun changeWeek(weeks: Long): String {
        startPeriod = startPeriod.plusWeeks(weeks)
        endPeriod = endPeriod.plusWeeks(weeks)
        getDatesOfWeek()
        getData()
        return dateInfo("week")
    }

    fun changeMonth(month: Long): String {
        startPeriod = startPeriod.plusMonths(month)
        endPeriod = endPeriod.plusMonths(month)
        Log.d("StatesOfApp", "$startPeriod, $endPeriod")
        getData()
        return dateInfo("month")
    }

    fun changeYear(year: Long): String {
        startPeriod = startPeriod.plusYears(year)
        endPeriod = endPeriod.plusYears(year)
        getData()
        return dateInfo("year")
    }

    fun getDatesOfDay() {
        endPeriod = startPeriod.plusDays(1)
    }

    private fun getDatesOfWeek() {
        while (startPeriod.dayOfWeek != DayOfWeek.MONDAY) {
            startPeriod = startPeriod.minusDays(1)
        }

        while (endPeriod.dayOfWeek != DayOfWeek.SUNDAY) {
            endPeriod = endPeriod.plusDays(1)
        }
    }

    fun getDatesOfMonth() {
        while (startPeriod.dayOfMonth != 1) {
            startPeriod = startPeriod.minusDays(1)
        }
        endPeriod = startPeriod.plusMonths(1)
    }

    fun getDatesOfYear() {
        while (startPeriod.dayOfMonth != 1) {
            startPeriod = startPeriod.minusDays(1)
        }
        while (startPeriod.monthValue != 1) {
            startPeriod = startPeriod.minusMonths(1)
        }
        endPeriod = startPeriod.plusYears(1)
    }

    fun periodDates(startDate: Long, endDate: Long) : String {
        val startDateDate = Date(startDate)
        val endDateDate = Date(endDate)
        Log.d("StatesOfApp", "$startDateDate, $endDateDate")
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val start = format.format(startDateDate)
        val end = format.format(endDateDate)
        Log.d("StatesOfApp", "$start, $end")
        startPeriod = LocalDate.parse(start)
        endPeriod = LocalDate.parse(end).plusDays(1)
        Log.d("StatesOfApp", "$startPeriod, $endPeriod")
        return dateInfo("period")
    }
}