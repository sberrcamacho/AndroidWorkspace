package com.example.appvuelos.data.dao

import androidx.room.*
import com.example.appvuelos.data.entities.ReservaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReservaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reserva: ReservaEntity)

    @Update
    suspend fun update(reserva: ReservaEntity)

    @Delete
    suspend fun delete(reserva: ReservaEntity)

    @Query("SELECT * FROM reservas ORDER BY id DESC")
    fun getAll(): Flow<List<ReservaEntity>>

    @Query("SELECT * FROM reservas WHERE id = :id")
    suspend fun getById(id: Int): ReservaEntity?
}