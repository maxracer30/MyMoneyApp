package ru.maxstelmakh.mymoney.domain.repository

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.maxstelmakh.mymoney.data.models.CategoryModelData
import ru.maxstelmakh.mymoney.data.models.EventInDetailModelData
import ru.maxstelmakh.mymoney.data.models.EventModelData
import ru.maxstelmakh.mymoney.data.models.StatisticModelData
import ru.maxstelmakh.mymoney.data.relations.CategoriesWithEvents

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

    @Query("""
        SELECT
    SUM(expense)
FROM EventModelData
WHERE joined_date BETWEEN date('now', 'start of month') AND date('now', 'start of month', '+1 month')
    """)
    fun getSumInMonth(): Flow<List<Int>>

    //----------------------------Categories--------------------------------------------------------

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(category: CategoryModelData)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCategory(category: CategoryModelData)

    @Delete
    suspend fun deleteCategory(category: CategoryModelData)

    @Query("SELECT * FROM CategoryModelData ORDER BY categoryId DESC")
    fun getAllCategories(): Flow<List<CategoryModelData>>

    @Query("SELECT * FROM CategoryModelData WHERE categoryName = :category")
    fun getCategoryOfEvents(category: String): Flow<List<CategoriesWithEvents>>

    @Query("""  
SELECT
    categoryId,
    categoryName, 
    color, 
    image, 
    SUM(expense) AS total,
    (ROUND((SELECT SUM(expense) * 1.0)/(SELECT SUM(expense) FROM EventModelData WHERE joined_date BETWEEN date(:startPeriod) AND date(:endPeriod)), 5)) AS percent
FROM CategoryModelData JOIN EventModelData
ON CategoryModelData.categoryId = EventModelData.category
WHERE joined_date BETWEEN date(:startPeriod) AND date(:endPeriod)
GROUP BY category
ORDER BY categoryId DESC""")
    fun getCategoriesForStatistic(startPeriod: String, endPeriod: String): List<StatisticModelData>

    @Query("""  SELECT
                    eventId,
                    expense,
                    categoryName, 
                    color,
                    description,
                    categoryId,
                    joined_date
                FROM EventModelData JOIN CategoryModelData
                ON CategoryModelData.categoryId = EventModelData.category
                ORDER BY datetime(joined_date) DESC""")
    fun getAllEventsInDetail(): Flow<List<EventInDetailModelData>>

    //----------------------------Transaction-------------------------------------------------------

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insertRef(ref: CategoryEventCrossRef)
//    @Transaction
//    @Query("SELECT * FROM CategoryModelData WHERE category = :category")
//    fun getAllEventsInCategory(category: String): List<CategoriesWithEvents>

}