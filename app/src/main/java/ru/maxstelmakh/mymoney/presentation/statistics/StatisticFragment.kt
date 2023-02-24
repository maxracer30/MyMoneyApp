package ru.maxstelmakh.mymoney.presentation.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.databinding.FragmentStatisticBinding
import ru.maxstelmakh.mymoney.presentation.adapter.piechartadapter.PieChartDiagram
import ru.maxstelmakh.mymoney.presentation.adapter.statisticadapter.MainStatisticAdapter

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

    private fun init() = with(binding) {

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