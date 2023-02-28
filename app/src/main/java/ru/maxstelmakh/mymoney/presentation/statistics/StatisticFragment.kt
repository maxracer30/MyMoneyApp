package ru.maxstelmakh.mymoney.presentation.statistics

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.databinding.FragmentStatisticBinding
import ru.maxstelmakh.mymoney.presentation.adapter.piechartadapter.PieChartDiagram
import ru.maxstelmakh.mymoney.presentation.adapter.statisticadapter.MainStatisticAdapter
import java.time.LocalDate
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

        weekInfo.text = viewModel.dateInfo
        periodGroup.check(R.id.weekPeriod)
        periodGroup.setOnCheckedChangeListener { _, checkedId ->
            run {

                viewModel.startPeriod = LocalDate.now()
                viewModel.endPeriod = LocalDate.now()

                when (checkedId) {
                    R.id.dayPeriod -> {

                        btnPreviewPeriod.setOnClickListener {

                        }
                        btnNextPeriod.setOnClickListener {

                        }
                        weekInfo.text = viewModel.dateInfo
                    }
                    R.id.weekPeriod -> {
                        weekInfo.text = viewModel.dateInfo
                        btnPreviewPeriod.setOnClickListener {
                            viewModel.minusWeek()
                            weekInfo.text = viewModel.dateInfo
                        }
                        btnNextPeriod.setOnClickListener {
                            viewModel.plusWeek()
                            weekInfo.text = viewModel.dateInfo
                        }
                    }
                    R.id.monthPeriod -> Toast.makeText(context, "m", Toast.LENGTH_SHORT).show()
                    R.id.yearPeriod -> Toast.makeText(context, "y", Toast.LENGTH_SHORT).show()
                    R.id.randomPeriod -> Toast.makeText(context, "p", Toast.LENGTH_SHORT).show()
                }
            }
        }

        statisticsRecyclerView.adapter = statAdapter

        viewModel.statistic.observe(viewLifecycleOwner) {
            viewModel.viewModelScope.launch {
                statAdapter.setList(it)
                PieChartDiagram(statisticPiechart).getChart(it)
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}