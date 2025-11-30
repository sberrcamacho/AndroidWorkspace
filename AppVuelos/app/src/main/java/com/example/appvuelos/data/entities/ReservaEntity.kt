package com.example.appvuelos.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reservas")
data class ReservaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val idViaje: Int,
    val idPasajero: Int,
    val asientos: Int
)