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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appvuelos.R
import com.example.appvuelos.application.RoomApplication
import com.example.appvuelos.ui.theme.DarkRed
import com.example.appvuelos.ui.theme.White
import com.example.appvuelos.ui.viewmodel.ReservasViewModel

enum class ReservaDialogMode { NONE, BUSCAR, ELIMINAR, ACTUALIZAR }

@Composable
fun PantallaReservas(
    modifier: Modifier = Modifier,
    toRegresar: (Int) -> Unit
) {
    // ViewModel connected directly to DAO
    val viewModel = remember {
        ReservasViewModel(
            RoomApplication.db.reservasDao(),
            RoomApplication.db.vuelosDao(),
            RoomApplication.db.pasajerosDao()
        )
    }

    // dialog mode & inputs
    var dialogMode by remember { mutableStateOf(ReservaDialogMode.NONE) }

    var vueloInput by remember { mutableStateOf("") }
    var pasajeroInput by remember { mutableStateOf("") }
    var asientoInput by remember { mutableStateOf("") }

    var vueloError by remember { mutableStateOf(false) }
    var pasajeroError by remember { mutableStateOf(false) }
    var asientoError by remember { mutableStateOf(false) }

    val vueloInt = vueloInput.toIntOrNull()
    val pasajeroInt = pasajeroInput.toIntOrNull()

    var nextId by remember { mutableIntStateOf(1) }
    fun refreshNextId() {
        viewModel.getNextReservaId { id -> nextId = id }
    }

    LaunchedEffect(Unit) {
        refreshNextId()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(White)
            .padding(28.dp)
            .wrapContentSize(Alignment.TopCenter)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = modifier.height(30.dp))

        BotonRegresar(
            toRegresar = { toRegresar(1) },
            modifier = Modifier.align(Alignment.Start).padding(bottom = 20.dp)
        )

        Text(
            text = "Gestión de Reservas",
            style = MaterialTheme.typography.displaySmall.copy(
                color = DarkRed,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.align(Alignment.Start)
        )

        Text(
            text = "En esta sección podrá administrar las reservas de vuelos realizadas por los pasajeros. \n" +
                    "El sistema permite registrar nuevas reservas, consultar las existentes, actualizarlas o eliminarlas según sea necesario. \n" +
                    "Cada reserva se vincula de manera precisa con el identificador del vuelo y del pasajero correspondiente, garantizando la coherencia de la información y el cumplimiento de las relaciones establecidas en la base de datos. \n" +
                    "La correcta gestión de las reservas asegura un control eficiente de la ocupación de los vuelos y facilita la organización de los servicios ofrecidos.",
            style = MaterialTheme.typography.bodyLarge.copy(),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 20.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        CampoID(
            label = stringResource(R.string.id_vuelos_text,nextId),
            icon = R.drawable.id_card_48dp_ffffff_fill0_wght400_grad0_opsz48,
            modifier = Modifier
                .align(Alignment.Start)
                .fillMaxWidth(0.5F)
                .padding(bottom = 20.dp, start = 20.dp)
        )

        // inputs
        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            val keyboardOptionsNumber = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
            val shape = 12.dp
            val fontSize = 22.sp
            val fieldModifier = Modifier
                .fillMaxWidth()
                .height(64.dp)

            EntradaDeTexto(
                value = vueloInput,
                onValueChange = {
                    vueloInput = it
                    vueloError = it.isBlank() || !it.all { ch -> ch.isDigit() }
                },
                label = R.string.vuelo_id_label,
                icon = R.drawable.airplane_ticket_48dp_ffffff_fill1_wght400_grad0_opsz48,
                keyboardOptions = keyboardOptionsNumber.copy(imeAction = ImeAction.Next),
                shape = shape,
                fontSize = fontSize,
                fontSizeInput = fontSize,
                isError = vueloError,
                textError = R.string.solo_numeros,
                modifier = fieldModifier
            )

            EntradaDeTexto(
                value = pasajeroInput,
                onValueChange = {
                    pasajeroInput = it
                    pasajeroError = it.isBlank() || !it.all { ch -> ch.isDigit() }
                },
                label = R.string.pasajero_id_label,
                icon = R.drawable.id_card_48dp_ffffff_fill0_wght400_grad0_opsz48,
                keyboardOptions = keyboardOptionsNumber.copy(imeAction = ImeAction.Next),
                shape = shape,
                fontSize = fontSize,
                fontSizeInput = fontSize,
                isError = pasajeroError,
                textError = R.string.solo_numeros,
                modifier = fieldModifier
            )

            EntradaDeTexto(
                value = asientoInput,
                onValueChange = {
                    asientoInput = it
                    asientoError = it.isBlank()
                },
                label = R.string.asiento_label,
                icon = R.drawable.event_seat_48dp_ffffff_fill1_wght400_grad0_opsz48,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                shape = shape,
                fontSize = fontSize,
                fontSizeInput = fontSize,
                isError = asientoError,
                textError = R.string.solo_alphanumerico, // create this string if not present
                modifier = fieldModifier
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // buttons
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val btnModifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
            val btnFontSize = 24.sp

            // ADD
            BotonCustomizable(
                text = R.string.agregar_reserva,
                onClick = {
                    // validate
                    vueloError = vueloInput.isBlank() || vueloInt == null
                    pasajeroError = pasajeroInput.isBlank() || pasajeroInt == null
                    asientoError = asientoInput.isBlank()

                    if (!vueloError && !pasajeroError && !asientoError) {
                        viewModel.addReserva(
                            idVuelo = vueloInt ?: 0,
                            idPasajero = pasajeroInt ?: 0,
                            asiento = asientoInput.trim()
                        ) {
                           // reset fields
                            vueloInput = ""
                            pasajeroInput = ""
                            asientoInput = ""
                            refreshNextId()
                        }
                    }
                },
                fontSize = btnFontSize,
                contentColor = White,
                containerColor = DarkRed,
                modifier = btnModifier
            )

            // READ (open dialog to ask for id)
            BotonCustomizable(
                text = R.string.leer_reserva,
                onClick = { dialogMode = ReservaDialogMode.BUSCAR },
                fontSize = btnFontSize,
                contentColor = White,
                containerColor = DarkRed,
                modifier = btnModifier
            )

            // UPDATE (open dialog to ask for id -> populate fields -> user edits -> confirm through same dialog flow)
            BotonCustomizable(
                text = R.string.actualizar_reserva,
                onClick = { dialogMode = ReservaDialogMode.ACTUALIZAR },
                fontSize = btnFontSize,
                contentColor = White,
                containerColor = DarkRed,
                modifier = btnModifier
            )

            // DELETE (open dialog to ask for id)
            BotonCustomizable(
                text = R.string.eliminar_reserva,
                onClick = { dialogMode = ReservaDialogMode.ELIMINAR },
                fontSize = btnFontSize,
                contentColor = White,
                containerColor = DarkRed,
                modifier = btnModifier
            )
        }

        Spacer(modifier = Modifier.height(30.dp))


        // Dialog handling (reuse DialogIdPasajero which expects an id)
        if (dialogMode != ReservaDialogMode.NONE) {
            DialogIdPasajero(
                title = when (dialogMode) {
                    ReservaDialogMode.ACTUALIZAR -> R.string.actualizar_dialog_id_title
                    ReservaDialogMode.BUSCAR -> R.string.buscar_dialog_id_title
                    ReservaDialogMode.ELIMINAR -> R.string.eliminar_dialog_id_title
                    ReservaDialogMode.NONE -> R.string.none_dialog_id_title
                },
                onDismiss = { dialogMode = ReservaDialogMode.NONE },
                onConfirm = { id ->
                    when (dialogMode) {
                        ReservaDialogMode.BUSCAR -> {
                            viewModel.getReservaById(id) { reserva ->
                                reserva?.let {
                                    vueloInput = it.idVuelo.toString()
                                    pasajeroInput = it.idPasajero.toString()
                                    asientoInput = it.asiento
                                    nextId = it.idReserva
                                }
                            }
                        }

                        ReservaDialogMode.ELIMINAR -> {
                            viewModel.deleteReservaById(id)
                            refreshNextId()
                        }

                        ReservaDialogMode.ACTUALIZAR -> {
                            // first fetch the reserva to ensure it exists and get its idReserva
                            viewModel.getReservaById(id) { reserva ->
                                reserva?.let {
                                    // validate current inputs before updating
                                    val validVuelo = vueloInput.isNotBlank() && vueloInt != null
                                    val validPasajero = pasajeroInput.isNotBlank() && pasajeroInt != null
                                    val validAsiento = asientoInput.isNotBlank()

                                    vueloError = !validVuelo
                                    pasajeroError = !validPasajero
                                    asientoError = !validAsiento

                                    if (validVuelo && validPasajero && validAsiento) {
                                        viewModel.updateReserva(
                                            idReserva = it.idReserva,
                                            idVuelo = vueloInt,
                                            idPasajero = pasajeroInt,
                                            asiento = asientoInput.trim()
                                        )
                                        // reset fields and refresh id
                                        vueloInput = ""
                                        pasajeroInput = ""
                                        asientoInput = ""
                                        refreshNextId()
                                    }
                                }
                            }
                        }

                        else -> {}
                    }
                    dialogMode = ReservaDialogMode.NONE
                }
            )
        }
    }
}




