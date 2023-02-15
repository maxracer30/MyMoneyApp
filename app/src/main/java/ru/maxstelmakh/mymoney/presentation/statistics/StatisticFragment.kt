package ru.maxstelmakh.mymoney.presentation.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.databinding.FragmentStatisticBinding
import ru.maxstelmakh.mymoney.presentation.adapter.piechartadapter.PieChartDiagram

class StatisticFragment : Fragment(R.layout.fragment_statistic) {
    private var _binding: FragmentStatisticBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatisticBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.statisticPiechart.apply {
            PieChartDiagram(this).getChart()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}