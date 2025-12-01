package com.example.appvuelos.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vuelos")
data class VuelosEntity(
    @PrimaryKey(autoGenerate = true) val idVuelo: Int = 0,
    val ciudadOrigen: String,
    val ciudadDestino: String,
    val fecha: Long,
    val hora: Long
)
