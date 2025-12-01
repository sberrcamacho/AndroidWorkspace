package com.example.appvuelos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appvuelos.data.dao.PasajerosDao
import com.example.appvuelos.data.entities.PasajerosEntity
import com.example.appvuelos.ui.screens.toValidNameOrUnknown
import kotlinx.coroutines.launch

class PasajerosViewModel(private val pasajerosDao: PasajerosDao) : ViewModel() {

    // Añadir pasajero con validación
    fun addPasajero(
        nombre: String,
        apellido: String,
        documento: Int?,
        telefono: Int?,
        onComplete: (() -> Unit)? = null
    ) {
        viewModelScope.launch {
            if (documento != null && telefono != null) {
                val pasajeroEntity = PasajerosEntity(
                    nombre = nombre.toValidNameOrUnknown(),
                    apellido = apellido.toValidNameOrUnknown(),
                    documento = documento,
                    telefono = telefono
                )
                pasajerosDao.insert(pasajeroEntity)
                onComplete?.invoke()
            }
        }
    }

    // Actualizar pasajero con validación
    fun updatePasajero(
        id: Int,
        nombre: String,
        apellido: String,
        documento: Int?,
        telefono: Int?,
        onComplete: (() -> Unit)? = null
    ) {
        getPasajeroById(id) { pasajero ->
            pasajero?.let {
                viewModelScope.launch {
                    if (documento != null && telefono != null) {
                        val updated = PasajerosEntity(
                            idPasajero = it.idPasajero,
                            nombre = nombre.toValidNameOrUnknown(),
                            apellido = apellido.toValidNameOrUnknown(),
                            documento = documento,
                            telefono = telefono
                        )
                        pasajerosDao.update(updated)
                        onComplete?.invoke()
                    }
                }
            }
        }
    }

    // Obtener pasajero por id con callback
    fun getPasajeroById(id: Int, onResult: (PasajerosEntity?) -> Unit) {
        viewModelScope.launch {
            val pasajero = pasajerosDao.getById(id)
            onResult(pasajero)
        }
    }

    // Obtener siguiente id
    fun getNextPasajeroId(callback: (Int) -> Unit) {
        viewModelScope.launch {
            val last = pasajerosDao.getLastPasajeroId()
            callback((last ?: 0) + 1)
        }
    }

    // Buscar pasajero y devolver datos como lista de strings
    fun buscarPasajero(id: Int, onResult: (List<String>) -> Unit) {
        getPasajeroById(id) { pasajero ->
            pasajero?.let {
                val nombre = it.nombre
                val apellido = it.apellido
                val documento = it.documento.toString()
                val telefono = it.telefono.toString()
                val idPasajero = it.idPasajero.toString()
                onResult(listOf(nombre, apellido, documento, telefono, idPasajero))
            }
        }
    }

    // Eliminar pasajero por id con callback opcional
    fun deletePasajeroById(id: Int, onComplete: (() -> Unit)? = null) {
        viewModelScope.launch {
            pasajerosDao.deleteById(id)
            onComplete?.invoke()
        }
    }

    // Obtener todos los pasajeros
    fun getAllPasajeros(onResult: (List<PasajerosEntity>) -> Unit) {
        viewModelScope.launch {
            val lista = pasajerosDao.getAll()
            onResult(lista)
        }
    }

    // Eliminar todos los pasajeros
    fun deleteAllPasajeros(onComplete: (() -> Unit)? = null) {
        viewModelScope.launch {
            pasajerosDao.deleteAll()
            onComplete?.invoke()
        }
    }
}
