package ru.maxstelmakh.mymoney.presentation.adapter.piechartadapter

import android.graphics.Color
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.domain.model.StatisticModelDomain


class PieChartDiagram(
    private var pieChart: PieChart?
) {

    private var statisticData = emptyList<StatisticModelDomain>()

    fun getChart(statistic: List<StatisticModelDomain>) {
        statisticData = statistic
        pieChart?.let {
            loadPieChartData(it)
            setupPieChart(it)
        }
    }

    private fun setupPieChart(pieChart: PieChart) {

        var totalSum = 0L
        statisticData.forEach {
            totalSum += it.total
        }

        pieChart.apply {
            isDrawHoleEnabled = true
            setUsePercentValues(true)
            setEntryLabelTextSize(9F)
            setEntryLabelColor(Color.BLACK)

            centerText = if (totalSum == 0L) {
                context.getString(R.string.total_spent_pie_zero)
            } else {
                context.getString(R.string.total_spent_pie) +
                        "\n${(Math.round(totalSum * 1.0))}" + context.getString(R.string.RUB)
            }
            setCenterTextSize(20f)
            isHighlightPerTapEnabled = false
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
        val colors: ArrayList<Int> = arrayListOf()
        statisticData.forEach {
            entries.add(PieEntry(it.percent, it.categoryName))
            colors.add(Color.parseColor(it.color))
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