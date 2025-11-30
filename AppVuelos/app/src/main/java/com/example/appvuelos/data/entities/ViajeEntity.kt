package com.example.appvuelos.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "viajes")
data class ViajeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val ciudadOrigen: String,
    val ciudadDestino: String,
    val fecha: String,
    val hora: String
)