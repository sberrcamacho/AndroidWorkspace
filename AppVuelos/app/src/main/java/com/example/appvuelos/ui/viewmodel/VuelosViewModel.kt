package com.example.appvuelos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appvuelos.data.dao.VuelosDao
import com.example.appvuelos.data.entities.VuelosEntity
import com.example.appvuelos.ui.screens.toFormattedDateString
import com.example.appvuelos.ui.screens.toFormattedHour
import com.example.appvuelos.ui.screens.toValidNameOrUnknown
import kotlinx.coroutines.launch

class VuelosViewModel(private val vuelosDao: VuelosDao): ViewModel() {

    fun addVuelo(
        origen: String,
        destino: String,
        fecha: Long?,
        hora: Long?,
        onComplete: (() -> Unit)? = null
    ) {
        viewModelScope.launch {
            if (fecha != null && hora != null) {
                val vueloEntity = VuelosEntity(
                    origen = origen.toValidNameOrUnknown(),
                    destino = destino.toValidNameOrUnknown(),
                    fecha = fecha,
                    hora = hora
                )

                vuelosDao.insert(vueloEntity)
                onComplete?.invoke()
            }
        }
    }


    fun updateVuelo(
        idVuelo: Int,
        origen: String,
        destino: String,
        fecha: Long?,
        hora: Long?,
        onComplete: (() -> Unit)? = null
    ) {
        getVueloById(idVuelo) { vuelo ->
            vuelo?.let {
                if (fecha != null && hora != null) {
                    viewModelScope.launch {
                        val vuelosEntity = VuelosEntity(
                            idVuelo = it.idVuelo,
                            origen = origen.toValidNameOrUnknown(),
                            destino = destino.toValidNameOrUnknown(),
                            fecha = fecha,
                            hora = hora
                        )
                        vuelosDao.update(vuelosEntity)
                        onComplete?.invoke()
                    }
                }
            }
        }
    }

    fun buscarVuelo(
        idVuelo: Int,
        onResult: (List<String>) -> Unit
    ) {
        getVueloById(idVuelo) { vuelo ->
            vuelo?.let {
                val ciudadOrigen = it.origen
                val ciudadDestino = it.destino
                val fechaInput = it.fecha.toFormattedDateString()
                val horaInput = it.hora.toFormattedHour()
                val nextId = it.idVuelo
                onResult(listOf(ciudadOrigen, ciudadDestino, fechaInput, horaInput, nextId.toString()))
            }
        }
    }

    fun getVueloById(idVuelo: Int, onResult: (VuelosEntity?) -> Unit) {
        viewModelScope.launch {
            val vuelo = vuelosDao.getById(idVuelo)
            onResult(vuelo)
        }
    }

    fun getNextVueloId(callback: (Int) -> Unit) {
        viewModelScope.launch {
            val last = vuelosDao.getLastVueloId()
            callback((last ?: 0) + 1)
        }
    }

    fun deleteVueloById(idVuelo: Int, onComplete: (() -> Unit)?) {
        viewModelScope.launch {
            vuelosDao.deleteById(idVuelo)
            onComplete?.invoke()
        }
    }

    fun getAllVuelos(onResult: (List<VuelosEntity>) -> Unit) {
        viewModelScope.launch {
            val vuelos = vuelosDao.getAll()
            onResult(vuelos)
        }
    }

}