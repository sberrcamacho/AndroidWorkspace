package com.example.appvuelos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appvuelos.data.dao.PasajerosDao
import com.example.appvuelos.data.entities.PasajerosEntity
import kotlinx.coroutines.launch

class PasajerosViewModel(private val pasajerosDao: PasajerosDao) : ViewModel() {
    fun addPasajero(
        nombre: String,
        apellido: String,
        documento: Int,
        telefono: Int,
        onComplete: (() -> Unit)? = null
    ) {
        viewModelScope.launch {
            val pasajeroEntity = PasajerosEntity(
                nombre = nombre,
                apellido = apellido,
                documento = documento,
                telefono = telefono
            )

            pasajerosDao.insert(pasajeroEntity)
            onComplete?.invoke()
        }
    }

    fun updatePasajero(
        id: Int,
        nombre: String,
        apellido: String,
        documento: Int,
        telefono: Int
    ) {
        val pasajerosEntity = PasajerosEntity(
            idPasajero = id,
            nombre = nombre,
            apellido = apellido,
            documento = documento,
            telefono = telefono
        )

        viewModelScope.launch { pasajerosDao.update(pasajerosEntity) }
    }

    fun getPasajeroById(id: Int, onResult: (PasajerosEntity?) -> Unit) {
        viewModelScope.launch {
            val pasajero = pasajerosDao.getById(id)
            onResult(pasajero)
        }
    }

    fun getNextPasajeroId(callback: (Int) -> Unit) {
        viewModelScope.launch {
            val last = pasajerosDao.getLastPasajeroId()
            callback((last ?: 0) + 1)
        }
    }

    fun deletePasajeroById(id: Int) {
        viewModelScope.launch {
            pasajerosDao.deleteById(id)
        }
    }
}