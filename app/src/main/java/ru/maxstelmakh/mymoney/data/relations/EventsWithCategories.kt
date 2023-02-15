package ru.maxstelmakh.mymoney.data.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ru.maxstelmakh.mymoney.data.models.CategoryModelData

data class EventsWithCategories(
    @Embedded val eventModelData: CategoryModelData,
    @Relation(
        parentColumn = "eventId",
        entityColumn = "categoryId",
        associateBy = Junction(CategoryEventCrossRef::class)
    )
    val events: List<CategoryModelData>
)
