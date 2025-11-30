package com.example.appvuelos

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.appvuelos.ui.screens.MenuApp
import com.example.appvuelos.ui.screens.PantallaPasajeros
import com.example.appvuelos.ui.screens.PantallaPrincipal
import com.example.appvuelos.ui.screens.PantallaReservas
import com.example.appvuelos.ui.screens.PantallaVuelos


@Composable
fun Logic() {
    var screen by remember { mutableIntStateOf(0) }

    when (screen) {
        0 -> PantallaPrincipal { screen = it }
        1 -> MenuApp { screen = it }
        2 -> PantallaVuelos { screen = it }
        3 -> PantallaPasajeros { screen = it }
        4 -> PantallaReservas { screen = it }
    }
}