package ru.maxstelmakh.mymoney.presentation.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.datepicker.MaterialDatePicker
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.databinding.FragmentMainBinding

class MainFragment() : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding
    private val viewModel by viewModels<MainViewModel>()

    private lateinit var pieChart: PieChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        pieChart = binding?.activityMainPiechart!!
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPieChart()
        loadPieChartData()
    }


    private fun showDataRangePicker() {
        val datePicker = MaterialDatePicker.Builder
            .dateRangePicker()
            .setTitleText("Select date")
            .build()
        datePicker.show(
            parentFragmentManager.beginTransaction(),
            "date_range_picker"
        )

    }

    private fun init() {


//        val recyclerView = binding?.rvEvents
//        recyclerView?.adapter = adapter
//        viewModel.events.observe(viewLifecycleOwner) {
//            adapter.setList(it)
//        }
    }

    private fun setupPieChart() {
        pieChart.apply {
            isDrawHoleEnabled = true
            setUsePercentValues(true)
            setEntryLabelTextSize(12F)
            setEntryLabelColor(Color.BLACK)
            centerText = "Totally 37 276 "
            setCenterTextSize(20f)
            description.isEnabled = false
        }

        pieChart.legend.apply {
            verticalAlignment = Legend.LegendVerticalAlignment.TOP
            horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            orientation = Legend.LegendOrientation.VERTICAL
            setDrawInside(false)
            isEnabled = true
        }
    }


    private fun loadPieChartData() {
        val entries: ArrayList<PieEntry> = arrayListOf()
        entries.add(PieEntry(0.2f, "Food & Dining"))
        entries.add(PieEntry(0.1f, "Medical"))
        entries.add(PieEntry(0.45f, "Old BMW"))
        entries.add(PieEntry(0.15f, "Housing"))
        entries.add(PieEntry(0.05f, "Entertainment"))
        entries.add(PieEntry(0.05f, "Electricity & Gas"))

        val colors: ArrayList<Int> = arrayListOf()
        for (color: Int in ColorTemplate.MATERIAL_COLORS) {
            colors.add(color)
        }

        for (color: Int in ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color)
        }

        val dataSet: PieDataSet = PieDataSet(entries, "Expense category")
        dataSet.setColors(colors)

        val data: PieData = PieData(dataSet)

        data.apply {
            setDrawValues(true)
            setValueFormatter(PercentFormatter(pieChart))
            setValueTextSize(11f)
            setValueTextColor(Color.BLACK)
        }

        pieChart.apply {
            setData(data)
            invalidate()
            animateY(1500, Easing.EaseInOutQuad)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}