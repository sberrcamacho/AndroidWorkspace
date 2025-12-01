package com.example.appvuelos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appvuelos.data.dao.PasajerosDao
import com.example.appvuelos.data.dao.ReservasDao
import com.example.appvuelos.data.dao.VuelosDao
import com.example.appvuelos.data.entities.ReservasEntity
import kotlinx.coroutines.launch

class ReservasViewModel(
    private val reservasDao: ReservasDao,
    private val vuelosDao: VuelosDao,
    private val pasajerosDao: PasajerosDao
) : ViewModel() {

    // Añadir reserva (ya tienes)
    fun addReserva(
        idVuelo: Int,
        idPasajero: Int,
        asiento: String,
        onComplete: (() -> Unit)? = null
    ) {
        viewModelScope.launch {
            val vuelo = vuelosDao.getById(idVuelo) ?: return@launch
            val pasajero = pasajerosDao.getById(idPasajero) ?: return@launch

            val reserva = ReservasEntity(
                idVuelo = idVuelo,
                idPasajero = idPasajero,
                asiento = asiento
            )
            reservasDao.insert(reserva)
            onComplete?.invoke()
        }
    }

    // Actualizar reserva (ya tienes)
    fun updateReserva(
        idReserva: Int,
        idVuelo: Int,
        idPasajero: Int,
        asiento: String,
        onComplete: (() -> Unit)? = null
    ) {
        viewModelScope.launch {
            val vuelo = vuelosDao.getById(idVuelo) ?: return@launch
            val pasajero = pasajerosDao.getById(idPasajero) ?: return@launch

            reservasDao.update(
                ReservasEntity(
                    idReserva = idReserva,
                    idVuelo = idVuelo,
                    idPasajero = idPasajero,
                    asiento = asiento
                )
            )
            onComplete?.invoke()
        }
    }

    // Obtener reserva por id con callback
    fun getReservaById(idReserva: Int, onResult: (ReservasEntity?) -> Unit) {
        viewModelScope.launch {
            val reserva = reservasDao.getById(idReserva)
            onResult(reserva)
        }
    }

    // Obtener siguiente ID de reserva
    fun getNextReservaId(callback: (Int) -> Unit) {
        viewModelScope.launch {
            val last = reservasDao.getLastReservaId()
            callback((last ?: 0) + 1)
        }
    }

    // Eliminar reserva por id con callback opcional
    fun deleteReservaById(idReserva: Int, onComplete: (() -> Unit)? = null) {
        viewModelScope.launch {
            reservasDao.deleteById(idReserva)
            onComplete?.invoke()
        }
    }

    // Obtener todas las reservas
    fun getAllReservas(onResult: (List<ReservasEntity>) -> Unit) {
        viewModelScope.launch {
            val lista = reservasDao.getAll()
            onResult(lista)
        }
    }

    // Eliminar todas las reservas
    fun deleteAllReservas(onComplete: (() -> Unit)? = null) {
        viewModelScope.launch {
            reservasDao.deleteAll()
            onComplete?.invoke()
        }
    }

    // Buscar reserva y devolver datos como lista de strings
    fun buscarReserva(idReserva: Int, onResult: (List<String>) -> Unit) {
        getReservaById(idReserva) { reserva ->
            reserva?.let {
                val vueloId = it.idVuelo.toString()
                val pasajeroId = it.idPasajero.toString()
                val asiento = it.asiento
                val id = it.idReserva.toString()
                onResult(listOf(vueloId, pasajeroId, asiento, id))
            }
        }
    }
}
