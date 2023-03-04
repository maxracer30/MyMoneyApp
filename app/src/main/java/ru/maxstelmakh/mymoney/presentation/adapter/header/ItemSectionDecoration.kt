package ru.maxstelmakh.mymoney.presentation.adapter.header

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.domain.model.EventInDetailModelDomain
import java.time.LocalDate
import java.time.Month
import java.time.OffsetDateTime
import java.time.format.TextStyle
import java.util.*

@Suppress("DEPRECATION")
class ItemSectionDecoration(
    private val context: Context,
    private val getItemList: () -> List<EventInDetailModelDomain>
) : RecyclerView.ItemDecoration() {


    private val dividerHeight = dipToPx(context, 0f)
    private val dividerPaint = Paint(Paint.ANTI_ALIAS_FLAG).also {
        it.color = Color.parseColor("#ff0000")
    }

    private val sectionItemWidth: Int by lazy {
        getScreenWidth(context)
    }

    private val sectionItemHeight: Int by lazy {
        dipToPx(context, 30f)
    }


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val layoutManager = parent.layoutManager
        if (layoutManager !is LinearLayoutManager) {
            return
        }
        if (LinearLayoutManager.VERTICAL != layoutManager.orientation) {
            return
        }



        val list = getItemList()
        if (list.isEmpty()) {
            return
        }

        val position = parent.getChildAdapterPosition(view)
        if (0 == position || -1 == position) {
            outRect.top = sectionItemHeight
            return
        }

        val currentModel = getItemList()[position]
        println(currentModel)
        val previousModel = getItemList()[position - 1]
        println(previousModel)


        if (dateHandler(currentModel.joined_date) != dateHandler(previousModel.joined_date)) {
            outRect.top = sectionItemHeight
        }
        else {
            outRect.top = dividerHeight
        }
    }

    private fun drawDivider(canvas: Canvas, childView: View) {
        canvas.drawRect(
            0f,
            (childView.top - dividerHeight).toFloat(),
            childView.right.toFloat(),
            childView.top.toFloat(),
            dividerPaint
        )
    }

    private fun drawSectionView(canvas: Canvas, text: String, top: Int){

        val view = HeaderViewHolder(context)
        view.setDate(text)

        val bitmap = getViewGroupBitmap(view)
        val bitmapCanvas = Canvas(bitmap)
        view.draw(bitmapCanvas)

        canvas.drawBitmap(bitmap, 0f, top.toFloat(), null)
    }

    private fun getViewGroupBitmap(viewGroup: ViewGroup): Bitmap {
        val layoutParams = ViewGroup.LayoutParams(sectionItemWidth, sectionItemHeight)
        viewGroup.layoutParams = layoutParams

        viewGroup.measure(
            View.MeasureSpec.makeMeasureSpec(sectionItemWidth, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(sectionItemHeight, View.MeasureSpec.EXACTLY)
        )

        viewGroup.layout(0, 0, sectionItemWidth, sectionItemHeight)

        val bitmap = Bitmap.createBitmap(viewGroup.width, viewGroup.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        viewGroup.draw(canvas)

        return bitmap
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        val childCount = parent.childCount

        for (i in 0 until childCount) {
            val childView: View = parent.getChildAt(i)
            val position: Int = parent.getChildAdapterPosition(childView)
            if (position == -1) {
                return
            }
            val itemModel = getItemList()[position]

            if(getItemList().isNotEmpty() &&
                (0 == position || dateHandler(itemModel.joined_date) != dateHandler(getItemList()[position-1].joined_date))) {

                val top = childView.top - sectionItemHeight
                drawSectionView(c, getDisplayDate(itemModel.joined_date), top)
            }
            else {
                drawDivider(c, childView)
            }
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val list = getItemList()

        if (list.isEmpty()) {
            return
        }

        val childCount = parent.childCount
        if (childCount == 0) {
            return
        }

        val firstView = parent.getChildAt(0)

        val position = parent.getChildAdapterPosition(firstView)
        val text = getDisplayDate(list[position].joined_date)
        val itemModel = list[position]

        val condition:Boolean = if (list.size == 1) {
            true
        } else {
            dateHandler(itemModel.joined_date) != dateHandler(list[position+1].joined_date)
        }

        drawSectionView(c, text, if (firstView.bottom <= sectionItemHeight && condition) {
            firstView.bottom - sectionItemHeight
        }
        else {
            0
        })
    }

    private fun dipToPx(context: Context, dipValue: Float): Int {
        return (dipValue * context.resources.displayMetrics.density).toInt()
    }

    private fun getScreenWidth(context: Context): Int {
        val outMetrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val display = context.display
            display?.getRealMetrics(outMetrics)
        } else {
            val display = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
            display.getMetrics(outMetrics)
        }
        return outMetrics.widthPixels
    }

    @SuppressLint("NewApi")
    fun dateHandler(date: OffsetDateTime): String {

//        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
//        val formatted = format.format(date)

//        val result = LocalDate.parse(date)

        return date.toLocalDate().toString()
    }

    @SuppressLint("NewApi")
    fun getDisplayDate(date: OffsetDateTime): String {
        return when (val dateForm = date.toLocalDate()) {
            LocalDate.now() -> {
                context.getString(R.string.todayyy)
            }
            (LocalDate.now().minusDays(1)) -> {
                context.getString(R.string.yesterday)
            }
            else -> {
                var month = Month
                    .of(dateForm.monthValue)
                    .getDisplayName(TextStyle.FULL, Locale.getDefault())
                month = month.substring(0, 1).uppercase(Locale.getDefault()) + month.substring(1)

                StringBuilder()
                    .append(dateForm.dayOfMonth)
                    .append(" ")
                    .append(month)
                    .append(" ")
                    .append(dateForm.year)
                    .toString()
            }

        }

    }
}