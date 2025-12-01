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

    fun addReserva(
        idVuelo: Int,
        idPasajero: Int,
        asiento: String,
        onComplete: (() -> Unit)? = null
    ) {
        viewModelScope.launch {

            // Validate flight
            val vuelo = vuelosDao.getById(idVuelo) ?: return@launch

            // Validate passenger
            val pasajero = pasajerosDao.getById(idPasajero) ?: return@launch

            // Both exist → insert
            val reserva = ReservasEntity(
                idVuelo = idVuelo,
                idPasajero = idPasajero,
                asiento = asiento
            )

            reservasDao.insert(reserva)
            onComplete?.invoke()
        }
    }

    fun updateReserva(
        idReserva: Int,
        idVuelo: Int,
        idPasajero: Int,
        asiento: String
    ) {
        viewModelScope.launch {

            // Validate flight
            val vuelo = vuelosDao.getById(idVuelo) ?: return@launch

            // Validate passenger
            val pasajero = pasajerosDao.getById(idPasajero) ?: return@launch

            // OK → update
            reservasDao.update(
                ReservasEntity(
                    idReserva = idReserva,
                    idVuelo = idVuelo,
                    idPasajero = idPasajero,
                    asiento = asiento
                )
            )
        }
    }

    fun getReservaById(idReserva: Int, onResult: (ReservasEntity?) -> Unit) {
        viewModelScope.launch {
            onResult(reservasDao.getById(idReserva))
        }
    }

    fun getNextReservaId(callback: (Int) -> Unit) {
        viewModelScope.launch {
            val last = reservasDao.getLastReservaId()
            callback((last ?: 0) + 1)
        }
    }

    fun deleteReservaById(idReserva: Int) {
        viewModelScope.launch {
            reservasDao.deleteById(idReserva)
        }
    }
}