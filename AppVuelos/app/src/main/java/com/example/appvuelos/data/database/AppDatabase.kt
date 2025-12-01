package com.example.appvuelos.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appvuelos.data.dao.PasajerosDao
import com.example.appvuelos.data.dao.ReservasDao
import com.example.appvuelos.data.dao.VuelosDao
import com.example.appvuelos.data.entities.PasajerosEntity
import com.example.appvuelos.data.entities.ReservasEntity
import com.example.appvuelos.data.entities.VuelosEntity

@Database(entities = [PasajerosEntity::class, VuelosEntity::class, ReservasEntity::class], version = 4)
abstract class AppDatabase: RoomDatabase() {
    abstract fun pasajerosDao(): PasajerosDao
    abstract fun vuelosDao(): VuelosDao
    abstract fun reservasDao(): ReservasDao
}