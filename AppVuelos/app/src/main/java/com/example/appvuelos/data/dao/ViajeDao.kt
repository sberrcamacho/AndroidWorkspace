package com.example.appvuelos.data.dao

import androidx.room.*
import com.example.appvuelos.data.entities.ViajeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ViajeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(viaje: ViajeEntity)

    @Update
    suspend fun update(viaje: ViajeEntity)

    @Delete
    suspend fun delete(viaje: ViajeEntity)

    @Query("SELECT * FROM viajes ORDER BY id DESC")
    fun getAll(): Flow<List<ViajeEntity>>

    @Query("SELECT * FROM viajes WHERE id = :id")
    suspend fun getById(id: Int): ViajeEntity?
}