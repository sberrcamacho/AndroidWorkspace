package com.example.appvuelos.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appvuelos.data.entities.VuelosEntity

@Dao
interface VuelosDao {
    @Insert
    suspend fun insert(vuelo: VuelosEntity)

    @Update
    suspend fun update(vuelo: VuelosEntity)

    @Query("SELECT * FROM vuelos WHERE idVuelo = :id")
    suspend fun getById(id: Int): VuelosEntity?

    @Query("SELECT MAX(idVuelo) FROM vuelos")
    suspend fun getLastVueloId(): Int?

    @Query("DELETE FROM vuelos WHERE idVuelo = :id")
    suspend fun deleteById(id: Int)

    // delete all
    @Query("DELETE FROM vuelos")
    suspend fun deleteAll()






}