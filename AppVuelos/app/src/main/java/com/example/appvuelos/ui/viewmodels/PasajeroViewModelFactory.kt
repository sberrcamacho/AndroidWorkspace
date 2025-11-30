package com.example.appvuelos.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appvuelos.data.dao.PasajeroDao

class PasajeroViewModelFactory(
    private val dao: PasajeroDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PasajeroViewModel::class.java)) {
            return PasajeroViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
