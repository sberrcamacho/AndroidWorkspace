package com.example.appvuelos.ui.dialogs

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appvuelos.R
import com.example.appvuelos.data.entities.PasajerosEntity
import com.example.appvuelos.data.entities.ReservasEntity
import com.example.appvuelos.data.entities.VuelosEntity
import com.example.appvuelos.ui.screens.BotonCustomizable
import com.example.appvuelos.ui.screens.EntradaDeTexto
import com.example.appvuelos.ui.screens.toFormattedShortDateString
import com.example.appvuelos.ui.screens.toFormattedShortHour
import com.example.appvuelos.ui.theme.DarkRed
import com.example.appvuelos.ui.theme.White
import com.example.appvuelos.ui.viewmodel.PasajerosViewModel
import com.example.appvuelos.ui.viewmodel.ReservasViewModel
import com.example.appvuelos.ui.viewmodel.VuelosViewModel

@Composable
fun VuelosDialogs(
    dialogMode: DialogMode,
    viewModel: VuelosViewModel,
    onDismiss: () -> Unit,
    ciudadOrigen: String?,
    ciudadDestino: String?,
    fechaMillis: Long?,
    horaMillis: Long?,
    nextId: Int,
    onUpdateInputs: (String, String, String, String, Int) -> Unit
) {
    var nextId by remember { mutableIntStateOf(nextId) }

    fun refreshNextId(onComplete: (Int) -> Unit = {}) {
        viewModel.getNextVueloId { id ->
            nextId = id
            onComplete(id)
        }
    }

    when (dialogMode) {
        DialogMode.MOSTRAR_TODOS -> {
            var vuelos by remember { mutableStateOf<List<VuelosEntity>>(emptyList()) }

            LaunchedEffect(Unit) {
                viewModel.getAllVuelos { lista -> vuelos = lista }
            }

            DialogVuelos(onDismiss = onDismiss, vuelos = vuelos)
        }

        DialogMode.ELIMINAR_TODOS -> {
            DialogDeleteAll(
                onDismiss = onDismiss,
                onConfirm = {
                    viewModel.deleteAllVuelos {
                        refreshNextId {
                            onUpdateInputs("","","","",nextId)
                        }
                    }
                }
            )
        }

        DialogMode.NONE -> {}

        else -> {
            DialogId(
                title = when (dialogMode) {
                    DialogMode.BUSCAR -> R.string.buscar_dialog_id_title
                    DialogMode.ACTUALIZAR -> R.string.actualizar_dialog_id_title
                    DialogMode.ELIMINAR -> R.string.eliminar_dialog_id_title
                    else -> R.string.none_dialog_id_title
                },
                onDismiss = onDismiss,
                onConfirm = { id ->
                    when (dialogMode) {
                        DialogMode.BUSCAR -> viewModel.buscarVuelo(id) { vuelo ->
                            onUpdateInputs(vuelo[0], vuelo[1], vuelo[2], vuelo[3], vuelo[4].toInt())
                        }
                        DialogMode.ACTUALIZAR -> viewModel.updateVuelo(id, ciudadOrigen, ciudadDestino, fechaMillis, horaMillis) {
                            refreshNextId {
                                onUpdateInputs("", "", "", "", nextId)
                            }
                        }
                        DialogMode.ELIMINAR -> viewModel.deleteVueloById(id) {
                            refreshNextId {
                                onUpdateInputs("", "", "", "", nextId)
                            }
                        }
                        else -> {}
                    }
                    onDismiss()
                }
            )
        }
    }
}

@Composable
fun PasajerosDialogs(
    dialogMode: DialogMode,
    viewModel: PasajerosViewModel,
    nombreInput: String?,
    apellidoInput: String?,
    documento: Int?,
    telefono: Int?,
    nextId: Int,
    onDismiss: () -> Unit,
    onUpdateInputs: (String, String, String, String, Int) -> Unit
) {
    var nextId by remember { mutableIntStateOf(nextId) }

    fun refreshNextId(onComplete: (Int) -> Unit = {}) {
        viewModel.getNextPasajeroId { id ->
            nextId = id
            onComplete(id)
        }
    }

    when (dialogMode) {

        DialogMode.MOSTRAR_TODOS -> {
            var pasajeros by remember { mutableStateOf<List<PasajerosEntity>>(emptyList()) }

            LaunchedEffect(Unit) {
                viewModel.getAllPasajeros { lista ->
                    pasajeros = lista
                }
            }

            DialogPasajeros(
                onDismiss = onDismiss,
                pasajeros = pasajeros
            )
        }

        DialogMode.ELIMINAR_TODOS -> {
            DialogDeleteAll(
                onDismiss = onDismiss,
                onConfirm = {
                    viewModel.deleteAllPasajeros {
                        refreshNextId {
                           onUpdateInputs("", "", "", "", nextId)
                        }
                    }
                }
            )
        }

        DialogMode.NONE -> {}

        else -> {
            DialogId(
                title = when (dialogMode) {
                    DialogMode.BUSCAR -> R.string.buscar_dialog_id_title
                    DialogMode.ACTUALIZAR -> R.string.actualizar_dialog_id_title
                    DialogMode.ELIMINAR -> R.string.eliminar_dialog_id_title
                    else -> R.string.none_dialog_id_title
                },
                onDismiss = onDismiss,
                onConfirm = { id ->
                    when (dialogMode) {
                        DialogMode.BUSCAR -> viewModel.buscarPasajero(id) { datos ->
                            onUpdateInputs(datos[0], datos[1], datos[2], datos[3], datos[4].toInt())
                        }

                        DialogMode.ACTUALIZAR -> viewModel.updatePasajero(
                            id,
                            nombreInput,
                            apellidoInput,
                            documento,
                            telefono
                        ) {
                            refreshNextId() {
                                onUpdateInputs("", "", "", "", nextId)
                            }
                        }

                        DialogMode.ELIMINAR -> viewModel.deletePasajeroById(id) {
                            refreshNextId() {
                                onUpdateInputs("", "", "", "", nextId)
                            }
                        }

                        else -> {}
                    }
                    onDismiss()
                }
            )
        }
    }
}

@Composable
fun ReservasDialogs(
    dialogMode: DialogMode,
    viewModel: ReservasViewModel,
    idVueloInput: Int?,
    idPasajeroInput: Int?,
    asientoInput: String,
    nextId: Int,
    onDismiss: () -> Unit,
    onUpdateInputs: (String, String, String, Int) -> Unit
) {
    var nextId by remember { mutableIntStateOf(nextId) }

    fun refreshNextId(onComplete: (Int) -> Unit = {}) {
        viewModel.getNextReservaId { id ->
            nextId = id
            onComplete(id)
        }
    }

    when (dialogMode) {

        DialogMode.MOSTRAR_TODOS -> {
            var reservas by remember { mutableStateOf<List<ReservasEntity>>(emptyList()) }

            LaunchedEffect(Unit) {
                viewModel.getAllReservas { lista ->
                    reservas = lista
                }
            }

            DialogReservas(
                onDismiss = onDismiss,
                reservas = reservas
            )
        }

        DialogMode.ELIMINAR_TODOS -> {
            DialogDeleteAll(
                onDismiss = onDismiss,
                onConfirm = {
                    viewModel.deleteAllReservas {
                        refreshNextId {
                            onUpdateInputs("", "", "", nextId)
                        }
                    }
                }
            )
        }

        DialogMode.NONE -> {}

        else -> {
            DialogId(
                title = when (dialogMode) {
                    DialogMode.BUSCAR -> R.string.buscar_dialog_id_title
                    DialogMode.ACTUALIZAR -> R.string.actualizar_dialog_id_title
                    DialogMode.ELIMINAR -> R.string.eliminar_dialog_id_title
                    else -> R.string.none_dialog_id_title
                },
                onDismiss = onDismiss,
                onConfirm = { id ->
                    when (dialogMode) {
                        DialogMode.BUSCAR -> viewModel.buscarReserva(id) { datos ->
                            onUpdateInputs(
                                datos[0],
                                datos[1],
                                datos[2],
                                datos[3].toInt()
                            )
                        }

                        DialogMode.ACTUALIZAR -> viewModel.updateReserva(
                            id,
                            idVueloInput ?: 0,
                            idPasajeroInput ?: 0,
                            asientoInput
                        ) {
                            refreshNextId() {
                                onUpdateInputs("", "", "", nextId)
                            }
                        }

                        DialogMode.ELIMINAR -> viewModel.deleteReservaById(id) {
                            refreshNextId() {
                                onUpdateInputs("", "", "", nextId)
                            }
                        }

                        else -> {}
                    }
                    onDismiss()
                }
            )
        }
    }
}


enum class DialogMode { NONE, BUSCAR, ELIMINAR, ACTUALIZAR, MOSTRAR_TODOS , ELIMINAR_TODOS }

@Composable
private fun DialogId(
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit,
    @StringRes title: Int,
) {
    var idInput by remember { mutableStateOf("") }
    var idError by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(title)) },
        text = {
            val keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )

            val shape = 12.dp
            val fontSize = 22.sp
            val modifier = Modifier
                .height(64.dp)

            EntradaDeTexto(
                value = idInput,
                onValueChange = { idInput = it ; idError = it.isBlank() || !it.all { char -> char.isDigit() } },
                label = R.string.id_label,
                icon = R.drawable.id_card_48dp_ffffff_fill0_wght400_grad0_opsz48,
                keyboardOptions = keyboardOptions.copy(imeAction = ImeAction.Next),
                shape = shape,
                fontSize = fontSize,
                fontSizeInput = fontSize,
                isError = idError,
                textError = R.string.solo_numeros,
                modifier = modifier
            )

        },
        confirmButton = {
            BotonCustomizable(
                text = R.string.confirmar_boton_datapicker,
                onClick = {
                    onConfirm(idInput.toIntOrNull() ?: 0)
                },
                fontSize = 14.sp,
                contentColor = White,
                containerColor = DarkRed
            )
        },
        dismissButton = {
            BotonCustomizable(
                text = R.string.cancelar_boton_datapicker,
                onClick = { onDismiss() },
                fontSize = 14.sp,
                contentColor = White,
                containerColor = DarkRed
            )
        }
    )
}

@Composable
private fun DialogDeleteAll(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Eliminar todos los vuelos") },
        text = { Text("¿Estás seguro de eliminar todos los vuelos?") },
        confirmButton = {
            BotonCustomizable(
                text = R.string.confirmar_boton_datapicker,
                onClick = { onConfirm(); onDismiss() },
                fontSize = 14.sp,
                contentColor = White,
                containerColor = DarkRed
            )
        },
        dismissButton = {
            BotonCustomizable(
                text = R.string.cancelar_boton_datapicker,
                onClick = { onDismiss() },
                fontSize = 14.sp,
                contentColor = White,
                containerColor = DarkRed
            )
        }
    )
}

@Composable
private fun DialogVuelos(
    onDismiss: () -> Unit,
    vuelos: List<VuelosEntity>
) {

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Lista de Vuelos") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Encabezado
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    val color = DarkRed
                    val fontWeight = FontWeight.Bold

                    Text("ID", color = color, fontWeight = fontWeight, modifier = Modifier.weight(0.8f))
                    Text("Origen", color = color, fontWeight = fontWeight, modifier = Modifier.weight(2f))
                    Text("Destino", color = color, fontWeight = fontWeight, modifier = Modifier.weight(2f))
                    Text("Fecha", color = color, fontWeight = fontWeight, modifier = Modifier.weight(2f))
                    Text("Hora", color = color, fontWeight = fontWeight, modifier = Modifier.weight(1.2f))
                    }
                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                // Filas de datos
                LazyColumn(modifier = Modifier.height(220.dp)) {
                    items(vuelos) { vuelo ->
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(vuelo.idVuelo.toString(), fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.8f))
                            Text(vuelo.origen, modifier = Modifier.weight(2f))
                            Text(vuelo.destino, modifier = Modifier.weight(2f))
                            Text(vuelo.fecha.toFormattedShortDateString(), modifier = Modifier.weight(2f))
                            Text(vuelo.hora.toFormattedShortHour(), modifier = Modifier.weight(1.2f))
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                    }
                }
            }
        },
        confirmButton = {
            BotonCustomizable(
                text = R.string.cerrar_boton,
                onClick = { onDismiss() },
                fontSize = 14.sp,
                contentColor = White,
                containerColor = DarkRed
            )
        }
    )
}


@Composable
private fun DialogPasajeros(
    onDismiss: () -> Unit,
    pasajeros: List<PasajerosEntity>
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Lista de Pasajeros") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Encabezado
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val color = DarkRed
                    val fontWeight = FontWeight.Bold

                    Text("ID", color = color, fontWeight = fontWeight, modifier = Modifier.weight(0.8f))
                    Text("Nombre", color = color, fontWeight = fontWeight, modifier = Modifier.weight(2f))
                    Text("Apellido", color = color, fontWeight = fontWeight, modifier = Modifier.weight(2f))
                    Text("Doc", color = color, fontWeight = fontWeight, modifier = Modifier.weight(1.5f))
                    Text("Tel", color = color, fontWeight = fontWeight, modifier = Modifier.weight(1.5f))
                }
                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

                // Filas de datos
                LazyColumn(modifier = Modifier.height(220.dp)) {
                    items(pasajeros) { pasajero ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(pasajero.idPasajero.toString(), fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.8f))
                            Text(pasajero.nombre, modifier = Modifier.weight(2f))
                            Text(pasajero.apellido, modifier = Modifier.weight(2f))
                            Text(pasajero.documento.toString(), modifier = Modifier.weight(1.5f))
                            Text(pasajero.telefono.toString(), modifier = Modifier.weight(1.5f))
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                    }
                }
            }
        },
        confirmButton = {
            BotonCustomizable(
                text = R.string.cerrar_boton,
                onClick = { onDismiss() },
                fontSize = 14.sp,
                contentColor = White,
                containerColor = DarkRed
            )
        }
    )
}

@Composable
private fun DialogReservas(
    onDismiss: () -> Unit,
    reservas: List<ReservasEntity>
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Lista de Reservas") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Encabezado
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val color = DarkRed
                    val fontWeight = FontWeight.Bold

                    Text("ID", color = color, fontWeight = fontWeight, modifier = Modifier.weight(0.8f))
                    Text("Vuelo", color = color, fontWeight = fontWeight, modifier = Modifier.weight(1.5f))
                    Text("Pasajero", color = color, fontWeight = fontWeight, modifier = Modifier.weight(1.5f))
                    Text("Asiento", color = color, fontWeight = fontWeight, modifier = Modifier.weight(1.2f))
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

                // Filas de datos
                LazyColumn(modifier = Modifier.height(220.dp)) {
                    items(reservas) { reserva ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(reserva.idReserva.toString(), fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.8f))
                            Text(reserva.idVuelo.toString(), modifier = Modifier.weight(1.5f))
                            Text(reserva.idPasajero.toString(), modifier = Modifier.weight(1.5f))
                            Text(reserva.asiento, modifier = Modifier.weight(1.2f))
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                    }
                }
            }
        },
        confirmButton = {
            BotonCustomizable(
                text = R.string.cerrar_boton,
                onClick = { onDismiss() },
                fontSize = 14.sp,
                contentColor = White,
                containerColor = DarkRed
            )
        }
    )
}


