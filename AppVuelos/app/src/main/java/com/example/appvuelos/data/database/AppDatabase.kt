package com.example.appvuelos.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appvuelos.data.dao.*
import com.example.appvuelos.data.entities.*

@Database(
    entities = [
        ViajeEntity::class,
        PasajeroEntity::class,
        ReservaEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun viajeDao(): ViajeDao
    abstract fun pasajeroDao(): PasajeroDao
    abstract fun reservaDao(): ReservaDao
}