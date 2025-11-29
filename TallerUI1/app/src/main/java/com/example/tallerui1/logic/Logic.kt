package com.example.tallerui1.logic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.tallerui1.screens.MenuApp
import com.example.tallerui1.screens.SalesScreen

@Composable
fun MainApp() {
    var screen by remember { mutableStateOf(0) }
    when (screen) {
        0 -> MenuApp { screen = it }
        1 -> SalesScreen()
    }

}
