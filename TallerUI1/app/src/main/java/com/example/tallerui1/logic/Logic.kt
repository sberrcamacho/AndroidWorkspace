package com.example.tallerui1.logic

import NumerosScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.tallerui1.screens.AdivinaScreen
import com.example.tallerui1.screens.MenuApp
import com.example.tallerui1.screens.NotesScreen
import com.example.tallerui1.screens.SalarioScreen
import com.example.tallerui1.screens.VentasScreen

@Composable
fun MainApp() {
    var screen by remember { mutableIntStateOf(0) }
    when (screen) {
        0 -> MenuApp { screen = it }
        1 -> VentasScreen() { screen = it }
        2 -> NotesScreen() { screen = it }
        3 -> SalarioScreen() { screen = it }
        4 -> AdivinaScreen() {screen = it}
        5 -> NumerosScreen() {screen = it}
    }

}
