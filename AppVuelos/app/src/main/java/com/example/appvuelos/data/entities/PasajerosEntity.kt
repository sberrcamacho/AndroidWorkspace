package com.example.appvuelos.data.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "pasajeros",
    indices = [
        Index(value = ["documento"], unique = true),
        Index(value = ["telefono"], unique = true)
    ]
)
data class PasajerosEntity(
    @PrimaryKey(autoGenerate = true) val idPasajero: Int = 0,
    val nombre: String,
    val apellido: String,
    val documento: Int,
    val telefono: Int
)
