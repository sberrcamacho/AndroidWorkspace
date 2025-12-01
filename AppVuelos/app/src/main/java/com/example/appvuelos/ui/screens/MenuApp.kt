package com.example.appvuelos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appvuelos.R
import com.example.appvuelos.ui.theme.DarkRed
import com.example.appvuelos.ui.theme.LightGray
import com.example.appvuelos.ui.theme.White

@Composable
fun MenuApp(
    modifier: Modifier = Modifier,
    to: (Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(White)
            .padding(28.dp)
            .wrapContentSize(Alignment.TopCenter)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = modifier.height(48.dp))

        Text(
            text = "Bienvenido a Pigeon Airlines",
            style = MaterialTheme.typography.displaySmall.copy(
                color = DarkRed,
                fontWeight = FontWeight.Bold
            ),
            modifier = modifier.padding(bottom = 20.dp)
        )

        Text(
            text = "Desde este panel podrá gestionar de manera organizada las principales funciones del sistema.\n" +
                    "En la sección Vuelos encontrará las rutas disponibles, junto con sus horarios y detalles correspondientes.\n" +
                    "El apartado Pasajeros le permitirá registrar, actualizar o consultar la información de cada viajero.\n" +
                    "Finalmente, en Reservas podrá crear nuevas solicitudes, modificar registros existentes o verificar asientos asignados.\n" +
                    "\n" +
                    "Gracias por utilizar nuestros servicios. Su experiencia comienza aquí.",

            style = MaterialTheme.typography.bodyLarge.copy()
        )

        Spacer(modifier = modifier.height(68.dp))

        val modifier = Modifier
            .height(82.dp)
            .fillMaxWidth()

        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            BotonCustomizable(
                text = R.string.vuelos_boton_menu,
                onClick = { to(2) },
                fontSize = 32.sp,
                contentColor = Color.Black,
                containerColor = LightGray,
                modifier = modifier
            )

            BotonCustomizable(
                text = R.string.pasajeros_boton_menu,
                onClick = { to(3) },
                fontSize = 32.sp,
                contentColor = Color.Black,
                containerColor = LightGray,
                modifier = modifier
            )

            BotonCustomizable(
                text = R.string.reservas_boton_menu,
                onClick = { to(4) },
                fontSize = 32.sp,
                contentColor = Color.Black,
                containerColor = LightGray,
                modifier = modifier
            )

            BotonCustomizable(
                text = R.string.ayuda_boton_menu,
                onClick = { to(5) },
                fontSize = 32.sp,
                contentColor = Color.Black,
                containerColor = LightGray,
                modifier = modifier
            )

        }
    }
}



