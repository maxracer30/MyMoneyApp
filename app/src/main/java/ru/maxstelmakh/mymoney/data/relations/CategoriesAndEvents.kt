package ru.maxstelmakh.mymoney.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import ru.maxstelmakh.mymoney.data.models.CategoryModelData
import ru.maxstelmakh.mymoney.data.models.EventModelData

data class CategoriesAndEvents(
    @Embedded val categoryModelData: CategoryModelData,
    @Relation(
        parentColumn = "category",
        entityColumn = "category"
    )
    val events: List<EventModelData>
)
