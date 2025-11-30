package com.example.appvuelos.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pasajeros")
data class PasajeroEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val apellido: String,
    val documento: String,
    val telefono: String
)