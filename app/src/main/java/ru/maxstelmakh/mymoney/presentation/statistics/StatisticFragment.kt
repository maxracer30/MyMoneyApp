package ru.maxstelmakh.mymoney.presentation.statistics

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
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

    private val mLinearLayoutManager = LinearLayoutManager(context)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatisticBinding.inflate(layoutInflater, container, false)
        binding.statisticsRecyclerView.layoutManager = mLinearLayoutManager
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

            dateInfo.setInAnimation(context, R.anim.slide_in_left)
            dateInfo.setOutAnimation(context, R.anim.slide_out_right)

            when (checkedId) {
                R.id.dayPeriod -> {
                    showButtons(true)
                    viewModel.setNowDate()
                    viewModel.getDatesOfDay()
                    viewModel.updateData()
                    dateInfo.setText(viewModel.dateInfo("day"))

                    btnPreviewPeriod.setOnClickListener {
                        animatedPrevious()
                        dateInfo.setText(viewModel.changeDay(-1))
                    }
                    btnNextPeriod.setOnClickListener {
                        animatedNext()
                        dateInfo.setText(viewModel.changeDay(1))
                    }
                }
                R.id.weekPeriod -> {
                    showButtons(true)
                    viewModel.updateData()
                    dateInfo.setText(viewModel.dateInfo("week"))

                    btnPreviewPeriod.setOnClickListener {
                        animatedPrevious()
                        dateInfo.setText(viewModel.changeWeek(-1))
                    }
                    btnNextPeriod.setOnClickListener {
                        animatedNext()
                        dateInfo.setText(viewModel.changeWeek(1))
                    }
                }
                R.id.monthPeriod -> {
                    showButtons(true)
                    viewModel.setNowDate()
                    viewModel.getDatesOfMonth()
                    viewModel.updateData()
                    dateInfo.setText(viewModel.dateInfo("month"))

                    btnPreviewPeriod.setOnClickListener {
                        animatedPrevious()
                        dateInfo.setText(viewModel.changeMonth(-1))
                    }
                    btnNextPeriod.setOnClickListener {
                        animatedNext()
                        dateInfo.setText(viewModel.changeMonth(1))
                    }
                }
                R.id.yearPeriod -> {
                    showButtons(true)
                    viewModel.setNowDate()
                    viewModel.getDatesOfYear()
                    viewModel.updateData()
                    dateInfo.setText(viewModel.dateInfo("year"))

                    btnPreviewPeriod.setOnClickListener {
                        animatedPrevious()
                        dateInfo.setText(viewModel.changeYear(-1))
                    }
                    btnNextPeriod.setOnClickListener {
                        animatedNext()
                        dateInfo.setText(viewModel.changeYear(1))
                    }
                }
                R.id.randomPeriod -> {
                    var startDate: Long
                    var endDate: Long
                    showButtons(false)
                    randomPeriod.setOnClickListener {
                        startDate = 0L
                        endDate = 0L
                        val datePicker = MaterialDatePicker.Builder
                            .dateRangePicker()
                            .build()

                        datePicker.addOnPositiveButtonClickListener {
                            startDate = it.first
                            endDate = it.second
                            dateInfo.setText(viewModel.periodDates(startDate, endDate))
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

    private fun showButtons(case: Boolean) {
        when (case) {
            true -> {
                binding.btnPreviewPeriod.visibility = View.VISIBLE
                binding.btnNextPeriod.visibility = View.VISIBLE
            }
            else -> {
                binding.btnPreviewPeriod.visibility = View.GONE
                binding.btnNextPeriod.visibility = View.GONE
            }
        }
    }

    private fun animatedPrevious() {
        binding.dateInfo.setInAnimation(context, R.anim.slide_in_left)
        binding.dateInfo.setOutAnimation(context, R.anim.slide_out_right)
    }

    private fun animatedNext() {
        binding.dateInfo.setInAnimation(context, R.anim.slide_in_right)
        binding.dateInfo.setOutAnimation(context, R.anim.slide_out_left)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}