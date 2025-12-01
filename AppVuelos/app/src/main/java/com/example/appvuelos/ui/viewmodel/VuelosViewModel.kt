package com.example.appvuelos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appvuelos.data.dao.VuelosDao
import com.example.appvuelos.data.entities.VuelosEntity
import kotlinx.coroutines.launch

class VuelosViewModel(private val vuelosDao: VuelosDao): ViewModel() {

    fun addVuelo(
        ciudadOrigen: String,
        ciudadDestino: String,
        fecha: Long,
        hora: Long,
        onComplete: (() -> Unit)? = null
    ) {
        viewModelScope.launch {
            val vueloEntity = VuelosEntity(
                ciudadOrigen = ciudadOrigen,
                ciudadDestino = ciudadDestino,
                fecha = fecha,
                hora = hora
            )
            vuelosDao.insert(vueloEntity)
            onComplete?.invoke()
        }
    }

    fun updateVuelo(
        idVuelo: Int,
        ciudadOrigen: String,
        ciudadDestino: String,
        fecha: Long,
        hora: Long
    ) {
        viewModelScope.launch {
            val vueloEntity = VuelosEntity(
                idVuelo = idVuelo,
                ciudadOrigen = ciudadOrigen,
                ciudadDestino = ciudadDestino,
                fecha = fecha,
                hora = hora
            )
            vuelosDao.update(vueloEntity)
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

    fun deleteVueloById(idVuelo: Int) {
        viewModelScope.launch {
            vuelosDao.deleteById(idVuelo)
        }
    }

}