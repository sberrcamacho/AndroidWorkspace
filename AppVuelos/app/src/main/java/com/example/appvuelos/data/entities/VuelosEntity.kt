package com.example.appvuelos.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vuelos")
data class VuelosEntity(
    @PrimaryKey(autoGenerate = true) val idVuelo: Int = 0,
    val origen: String,
    val destino: String,
    val fecha: Long,
    val hora: Long
)
