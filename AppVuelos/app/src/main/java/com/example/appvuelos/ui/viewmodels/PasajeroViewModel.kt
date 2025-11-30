package com.example.appvuelos.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appvuelos.data.dao.PasajeroDao
import com.example.appvuelos.data.entities.PasajeroEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PasajeroViewModel(private val dao: PasajeroDao) : ViewModel() {

    // Estado del pasajero encontrado
    private val _pasajeroEncontrado = mutableStateOf<PasajeroEntity?>(null)
    val pasajeroEncontrado: State<PasajeroEntity?> = _pasajeroEncontrado

    // LEER por documento
    fun leer(doc: String) {
        viewModelScope.launch {
            _pasajeroEncontrado.value = dao.buscarPorDocumento(doc)
        }
    }

    // AGREGAR nuevo
    fun agregar(nombre: String, apellido: String, documento: String, telefono: String) {
        viewModelScope.launch {
            dao.insertar(
                PasajeroEntity(
                    nombre = nombre,
                    apellido = apellido,
                    documento = documento,
                    telefono = telefono
                )
            )
        }
    }

    // ACTUALIZAR solo si existe
    fun actualizar(
        nombre: String,
        apellido: String,
        documento: String,
        telefono: String
    ) {
        viewModelScope.launch {
            val encontrado = dao.buscarPorDocumento(documento)
            if (encontrado != null) {
                val actualizado = encontrado.copy(
                    nombre = nombre,
                    apellido = apellido,
                    telefono = telefono
                )
                dao.actualizar(actualizado)
            }
        }
    }

    // ELIMINAR por documento
    fun eliminar(doc: String) {
        viewModelScope.launch {
            val encontrado = dao.buscarPorDocumento(doc)
            if (encontrado != null) {
                dao.eliminar(encontrado)
                _pasajeroEncontrado.value = null
            }
        }
    }
}

