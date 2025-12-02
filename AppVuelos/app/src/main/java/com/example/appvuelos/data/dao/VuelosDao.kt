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

    @Query("""
        SELECT seq 
        FROM sqlite_sequence 
        WHERE name = 'vuelos'
    """)
    suspend fun getLastVueloId(): Int?

    @Query("DELETE FROM vuelos WHERE idVuelo = :id")
    suspend fun deleteById(id: Int)

    // get all
    @Query("SELECT * FROM vuelos")
    suspend fun getAll(): List<VuelosEntity>

    // delete all
    @Query("DELETE FROM vuelos")
    suspend fun deleteAll()

}