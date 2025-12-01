package com.example.appvuelos.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "reservas",
    foreignKeys = [
        ForeignKey(
            entity = VuelosEntity::class,
            parentColumns = ["idVuelo"],
            childColumns = ["idVuelo"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PasajerosEntity::class,
            parentColumns = ["idPasajero"],
            childColumns = ["idPasajero"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["idVuelo"], unique = true),
        Index(value = ["idPasajero"], unique = true)
    ]
)
data class ReservasEntity(
    @PrimaryKey(autoGenerate = true) val idReserva: Int = 0,
    val idVuelo: Int,
    val idPasajero: Int,
    val asiento: String
)
