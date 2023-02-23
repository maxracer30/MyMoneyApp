package ru.maxstelmakh.mymoney.presentation.adapter.piechartadapter

import android.graphics.Color
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate


class PieChartDiagram(
    private var pieChart: PieChart?
) {
    fun getChart() {
        pieChart?.let {
            loadPieChartData(it)
            setupPieChart(it)
        }
    }

    private fun setupPieChart(pieChart: PieChart) {
        pieChart.apply {
            isDrawHoleEnabled = true
            setUsePercentValues(true)
            setEntryLabelTextSize(9F)
            setEntryLabelColor(Color.BLACK)
            centerText = ""
            setCenterTextSize(20f)
            isHighlightPerTapEnabled = true
            description.isEnabled = false
        }

        pieChart.legend.apply {
            verticalAlignment = Legend.LegendVerticalAlignment.TOP
            horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            orientation = Legend.LegendOrientation.VERTICAL
            setDrawInside(false)
            isEnabled = false
        }
    }

    private fun loadPieChartData(pieChart: PieChart) {
        val entries: ArrayList<PieEntry> = arrayListOf()
        entries.add(PieEntry(0.2f, "Food"))
        entries.add(PieEntry(0.1f, "Medical"))
        entries.add(PieEntry(0.45f, "Old BMW"))
        entries.add(PieEntry(0.15f, "Housing"))
        entries.add(PieEntry(0.05f, "Entertainment"))
        entries.add(PieEntry(0.05f, "Gas"))

        val colors: ArrayList<Int> = arrayListOf()
        for (color: Int in ColorTemplate.MATERIAL_COLORS) {
            colors.add(color)
        }
        for (color: Int in ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color)
        }

        val dataSet: PieDataSet = PieDataSet(entries, "Expense category")
        dataSet.colors = colors

        val data: PieData = PieData(dataSet)

        data.apply {
            setDrawValues(true)
            setValueFormatter(PercentFormatter(pieChart))
            setValueTextSize(12f)
            setValueTextColor(Color.BLACK)
        }

        pieChart.apply {
            setData(data)
            invalidate()
            animateY(1500, Easing.EaseInOutQuad)
        }
    }
}