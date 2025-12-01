package com.example.appvuelos.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appvuelos.data.entities.ReservasEntity

@Dao
interface ReservasDao {

    @Insert
    suspend fun insert(reservasEntity: ReservasEntity)

    @Update
    suspend fun update(reservasEntity: ReservasEntity)

    @Query("SELECT * FROM reservas WHERE idReserva = :id")
    suspend fun getById(id: Int): ReservasEntity?

    @Query("SELECT MAX(idReserva) FROM reservas")
    suspend fun getLastReservaId(): Int?

    @Query("DELETE FROM reservas WHERE idReserva = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * FROM reservas")
    suspend fun getAll(): List<ReservasEntity>

    @Query("DELETE FROM reservas")
    suspend fun deleteAll()
}