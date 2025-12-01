package com.example.appvuelos.application

import android.app.Application
import androidx.room.Room
import com.example.appvuelos.data.database.AppDatabase

class RoomApplication: Application() {
    companion object {
        lateinit var db: AppDatabase
    }

    // agregar .fallbackToDestructiveMigration() si se va cambiar el schema despues esta en version = 4
    override fun onCreate() {
        super.onCreate()
        
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "database-pasajeros"
        ).build()
    }
}