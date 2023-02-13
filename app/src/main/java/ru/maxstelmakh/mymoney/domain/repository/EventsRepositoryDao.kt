package ru.maxstelmakh.mymoney.domain.repository

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.maxstelmakh.mymoney.data.models.CategoryModelData
import ru.maxstelmakh.mymoney.data.models.EventModelData

@Dao
interface EventsRepositoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEvent(event: EventModelData)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(category: CategoryModelData)

    @Delete
    suspend fun deleteEvent(event: EventModelData)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateEvent(event: EventModelData)

    @Query("SELECT * FROM EventModelData ORDER BY datetime(joined_date)")
    fun getAllEvents(): Flow<List<EventModelData>>

    @Query("SELECT * FROM category")
    fun getAllCategories(): List<CategoryModelData>

    @Transaction
    @Query("SELECT * FROM EventModelData WHERE category = :category")
    fun getAllEventsInCategory(category: String): List<EventModelData>

}