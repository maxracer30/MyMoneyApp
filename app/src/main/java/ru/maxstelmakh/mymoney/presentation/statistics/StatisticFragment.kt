package ru.maxstelmakh.mymoney.presentation.statistics

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.databinding.FragmentStatisticBinding
import ru.maxstelmakh.mymoney.presentation.adapter.piechartadapter.PieChartDiagram
import ru.maxstelmakh.mymoney.presentation.adapter.statisticadapter.MainStatisticAdapter
import java.util.*

@AndroidEntryPoint
class StatisticFragment : Fragment(R.layout.fragment_statistic) {
    private var _binding: FragmentStatisticBinding? = null
    private val binding get() = _binding!!

    private val statAdapter = MainStatisticAdapter()
    private val viewModel by viewModels<StatisticViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatisticBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    @SuppressLint("NewApi")
    private fun init() = with(binding) {

        periodGroup.setOnCheckedChangeListener { _, checkedId ->

            with(viewModel) {
                setNowDate()
                startStatistic()
            }

            when (checkedId) {
                R.id.dayPeriod -> {
                    viewModel.setNowDate()
                    viewModel.getDatesOfDay()
                    viewModel.updateData()
                    dateInfo.text = viewModel.dateInfo("day")

                    btnPreviewPeriod.setOnClickListener {
                        dateInfo.text = viewModel.changeDay(-1)
                    }
                    btnNextPeriod.setOnClickListener {
                        dateInfo.text = viewModel.changeDay(1)
                    }
                }
                R.id.weekPeriod -> {
                    viewModel.updateData()
                    dateInfo.text = viewModel.dateInfo("week")

                    btnPreviewPeriod.setOnClickListener {
                        dateInfo.text = viewModel.changeWeek(-1)
                    }
                    btnNextPeriod.setOnClickListener {
                        dateInfo.text = viewModel.changeWeek(1)
                    }
                }
                R.id.monthPeriod -> {
                    viewModel.setNowDate()
                    viewModel.getDatesOfMonth()
                    viewModel.updateData()
                    dateInfo.text = viewModel.dateInfo("month")

                    btnPreviewPeriod.setOnClickListener {
                        dateInfo.text = viewModel.changeMonth(-1)
                    }
                    btnNextPeriod.setOnClickListener {
                        dateInfo.text = viewModel.changeMonth(1)
                    }
                }
                R.id.yearPeriod -> {
                    viewModel.setNowDate()
                    viewModel.getDatesOfYear()
                    viewModel.updateData()
                    dateInfo.text = viewModel.dateInfo("year")

                    btnPreviewPeriod.setOnClickListener {
                        dateInfo.text = viewModel.changeYear(-1)
                    }
                    btnNextPeriod.setOnClickListener {
                        dateInfo.text = viewModel.changeYear(1)
                    }
                }
                R.id.randomPeriod -> {
                    var startDate = 0L
                    var endDate = 0L
                    randomPeriod.setOnClickListener {
                        startDate = 0L
                        endDate = 0L
                        val datePicker = MaterialDatePicker.Builder
                            .dateRangePicker()
                            .build()

                        datePicker.addOnPositiveButtonClickListener {
                            startDate = it.first
                            endDate = it.second
                            dateInfo.text = viewModel.periodDates(startDate, endDate)
                            viewModel.updateData()
                        }
                        datePicker.show(
                            parentFragmentManager.beginTransaction(),
                            "date_range_picker"
                        )
                    }
                }
            }
        }
        periodGroup.check(R.id.weekPeriod)

        statisticsRecyclerView.adapter = statAdapter

        viewModel.statistic.observe(viewLifecycleOwner) {
            viewModel.viewModelScope.launch {
                statAdapter.setList(it)
                PieChartDiagram(statisticPiechart).getChart(it)
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun showDataRangePicker() {


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}