package ru.maxstelmakh.mymoney.domain.repository

import androidx.room.*
import ru.maxstelmakh.mymoney.data.models.EventModelData
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain

@Dao
interface EventsRepositoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEvent(event: EventModelData)

    @Delete
    suspend fun deleteEvent(event: EventModelData)

    @Update
    suspend fun updateEvent(event: EventModelData)

    @Query("SELECT * FROM eventmodeldata")
    suspend fun getAllEvents(): List<EventModelData>


}