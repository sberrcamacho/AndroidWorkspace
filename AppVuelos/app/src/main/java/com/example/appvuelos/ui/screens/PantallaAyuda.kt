package com.example.appvuelos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appvuelos.ui.theme.DarkRed
import com.example.appvuelos.ui.theme.White

@Composable
fun PantallaAyuda(
    modifier: Modifier = Modifier,
    toRegresar: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .padding(28.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        BotonRegresar(
            toRegresar = { toRegresar(1) },
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 20.dp)
        )

        Text(
            text = "Ayuda y Guía de Uso",
            style = MaterialTheme.typography.headlineMedium.copy(
                color = DarkRed,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 20.dp)
        )

        Text(
            text = buildAnnotatedString {
                append("Bienvenido a la aplicación de gestión de vuelos, pasajeros y reservas.\n\n")

                pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                append("1. Vuelos: ")
                pop()
                append("Aquí puede agregar, actualizar, buscar o eliminar vuelos. Para agregar un vuelo, complete todos los campos obligatorios como ciudad de origen, destino, fecha y hora. Para actualizar, seleccione el vuelo por ID. Puede ver la lista completa de vuelos para revisar la información registrada.\n\n")

                pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                append("2. Pasajeros: ")
                pop()
                append("Permite registrar pasajeros vinculados a los vuelos. Complete nombre, apellido, documento y teléfono para registrar un nuevo pasajero. También puede buscar, actualizar o eliminar pasajeros existentes mediante su ID.\n\n")

                pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                append("3. Reservas: ")
                pop()
                append("Desde esta sección puede asignar pasajeros a vuelos. Seleccione el vuelo y el pasajero correspondiente, y asigne un número de asiento. También puede actualizar o eliminar reservas existentes usando su ID.\n\n")

                pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                append("4. Validaciones: ")
                pop()
                append("Todos los campos obligatorios deben estar completos y con el formato correcto. Por ejemplo, nombres y apellidos solo deben contener letras, documentos y teléfonos solo números, y la fecha/hora deben ser válidas.\n\n")

                pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                append("5. Listados y Eliminación Masiva: ")
                pop()
                append("Puede consultar la lista completa de vuelos, pasajeros o reservas. También es posible eliminar todos los registros de una categoría mediante los botones correspondientes, confirmando la acción en el diálogo de seguridad.\n\n")

                append("Esta guía le permitirá navegar y utilizar la aplicación de manera correcta para gestionar su información de vuelos y pasajeros de forma eficiente.")
            },
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
            modifier = Modifier.padding(bottom = 20.dp)
        )

    }
}
