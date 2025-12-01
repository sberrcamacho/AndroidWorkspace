package com.example.appvuelos.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.appvuelos.data.entities.PasajerosEntity

@Dao
interface PasajerosDao {
    // Principales funciones
    // Update actualiza en base a la primary key

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pasajero: PasajerosEntity)

    @Update
    suspend fun update(user: PasajerosEntity)

    @Query("SELECT * FROM pasajeros WHERE idPasajero = :id")
    suspend fun getById(id: Int): PasajerosEntity?

    @Query("SELECT MAX(idPasajero) FROM pasajeros")
    suspend fun getLastPasajeroId(): Int?

    @Query("DELETE FROM pasajeros WHERE idPasajero = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * FROM pasajeros")
    suspend fun getAll(): List<PasajerosEntity>

    // delete all
    @Query("DELETE FROM pasajeros")
    suspend fun deleteAll()
}