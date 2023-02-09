package ru.maxstelmakh.mymoney.presentation.adapter.swipegesture

import android.content.Context
import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.qualifiers.ApplicationContext
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import ru.maxstelmakh.mymoney.R

abstract class SwipeGesture(
    @ApplicationContext private val context: Context
): ItemTouchHelper.SimpleCallback(
    0,
    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
) {
    private val deleteIcon = R.drawable.baseline_delete_sweep_24
    private val updateIcon = R.drawable.baseline_create_24

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {

        RecyclerViewSwipeDecorator.Builder(
            c,
            recyclerView,
            viewHolder,
            dX,
            dY,
            actionState,
            isCurrentlyActive
        )
            .addSwipeLeftBackgroundColor(context.getColor(R.color.red))
            .addSwipeLeftActionIcon(deleteIcon)
            .addSwipeRightActionIcon(updateIcon)
            .addSwipeRightBackgroundColor(context.getColor(R.color.lightblue))
            .addSwipeLeftLabel(context.getString(R.string.delete))
            .addSwipeRightLabel(context.getString(R.string.change))
            .addSwipeLeftCornerRadius(0, 1f)
            .create()
            .decorate()

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}


























