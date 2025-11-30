package com.example.appvuelos.data.dao

import androidx.room.*
import com.example.appvuelos.data.entities.PasajeroEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PasajeroDao {

    @Insert
    suspend fun insertar(pasajero: PasajeroEntity)

    @Update
    suspend fun actualizar(pasajero: PasajeroEntity)

    @Delete
    suspend fun eliminar(pasajero: PasajeroEntity)

    @Query("SELECT * FROM pasajeros WHERE documento = :doc LIMIT 1")
    suspend fun buscarPorDocumento(doc: String): PasajeroEntity?
}
