package ru.maxstelmakh.mymoney.domain.repository

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.maxstelmakh.mymoney.data.models.CategoryModelData
import ru.maxstelmakh.mymoney.data.models.EventModelData
import ru.maxstelmakh.mymoney.data.relations.CategoriesWithEvents
import ru.maxstelmakh.mymoney.data.relations.CategoryEventCrossRef

@Dao
interface EventsRepositoryDao {

    //------------------------Events----------------------------------------------------------------
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEvent(event: EventModelData)

    @Delete
    suspend fun deleteEvent(event: EventModelData)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateEvent(event: EventModelData)

    @Query("SELECT * FROM EventModelData ORDER BY datetime(joined_date) DESC")
    fun getAllEvents(): Flow<List<EventModelData>>

    @Query("SELECT eventId FROM EventModelData ORDER BY datetime(joined_date) DESC")
    fun getIdLastEvent(): Long

    //----------------------------Categories--------------------------------------------------------

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(category: CategoryModelData)

    @Query("SELECT * FROM CategoryModelData")
    fun getAllCategories(): Flow<List<CategoryModelData>>

    @Query("SELECT * FROM CategoryModelData WHERE category = :category")
    fun getCategoryOfEvents(category: String): Flow<List<CategoriesWithEvents>>

    //----------------------------Transaction-------------------------------------------------------

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRef(ref: CategoryEventCrossRef)
    @Transaction
    @Query("SELECT * FROM CategoryModelData WHERE category = :category")
    fun getAllEventsInCategory(category: String): List<CategoriesWithEvents>

}