package com.example.appvuelos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appvuelos.R
import com.example.appvuelos.application.RoomApplication
import com.example.appvuelos.data.entities.VuelosEntity
import com.example.appvuelos.ui.dialogs.DialogMode
import com.example.appvuelos.ui.dialogs.VuelosDialogs
import com.example.appvuelos.ui.theme.DarkRed
import com.example.appvuelos.ui.theme.White
import com.example.appvuelos.ui.viewmodel.VuelosViewModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun PantallaVuelos(
    modifier: Modifier = Modifier,
    toRegresar: (Int) -> Unit
) {
    val viewModel = remember { VuelosViewModel(RoomApplication.db.vuelosDao()) }

    // UI States
    var dialogMode by remember { mutableStateOf(DialogMode.NONE) }

    var ciudadOrigen by remember { mutableStateOf("") }
    var ciudadDestino by remember { mutableStateOf("") }
    var fechaInput by remember { mutableStateOf("") }
    var horaInput by remember { mutableStateOf("") }

    var ciudadOrigenError by remember { mutableStateOf(false) }
    var ciudadDestinoError by remember { mutableStateOf(false) }

    var fechaMillis by remember { mutableStateOf<Long?>(null) }
    var horaMillis by remember { mutableStateOf<Long?>(null) }

    var showDatePicker by remember { mutableStateOf(false) }
    var showHourPicker by remember { mutableStateOf(false) }

    var nextId by remember { mutableIntStateOf(1) }

    fun refreshNextId() {
        viewModel.getNextVueloId { nextId = it }
    }

    LaunchedEffect(Unit) { refreshNextId() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(White)
            .padding(28.dp)
            .wrapContentSize(Alignment.TopCenter)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        BotonRegresar(
            toRegresar = { toRegresar(1) },
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 20.dp)
        )

        Text(
            text = "Gestión de Vuelos",
            style = MaterialTheme.typography.displaySmall.copy(
                color = DarkRed,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.align(Alignment.Start)
        )


        Text(
            text = "En este módulo podrá administrar toda la información relacionada con los vuelos disponibles.\n" +
                    "Aquí es posible registrar nuevos vuelos, actualizar datos existentes y consultar rutas programadas.\n" +
                    "Introduzca cuidadosamente los datos requeridos, como ciudad de origen, ciudad de destino, fecha y hora, para garantizar un control preciso de las operaciones.\n" +
                    "\n" +
                    "Utilice esta sección para mantener actualizada la información que permitirá una adecuada coordinación del servicio aéreo.",
            style = MaterialTheme.typography.bodyLarge.copy(),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 20.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        CampoID(
            label = stringResource(R.string.id_vuelos_text, nextId),
            icon = R.drawable.airplane_ticket_48dp_ffffff_fill1_wght400_grad0_opsz48,
            modifier = Modifier
                .align(Alignment.Start)
                .fillMaxWidth(0.5F)
                .padding(bottom = 20.dp, start = 20.dp)
        )

        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {

            val shape = 12.dp
            val fontSize = 22.sp
            val inputModifier = Modifier
                .fillMaxWidth()
                .height(64.dp)

            EntradaDeTexto(
                value = ciudadOrigen,
                onValueChange = { ciudadOrigen = it ; ciudadOrigenError = it.isEmpty() || !it.all { c -> c.isLetter() }},
                label = R.string.ciudad_de_origen_label,
                icon = R.drawable.flight_takeoff_48dp_ffffff_fill0_wght400_grad0_opsz48,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                isError = ciudadOrigenError,
                textError = R.string.solo_letras,
                shape = shape,
                fontSize = fontSize,
                fontSizeInput = fontSize,
                modifier = inputModifier
            )

            EntradaDeTexto(
                value = ciudadDestino,
                onValueChange = { ciudadDestino = it ; ciudadDestinoError = it.isEmpty() || !it.all { c -> c.isLetter() } },
                label = R.string.ciudad_de_destino_label,
                icon = R.drawable.flight_land_48dp_ffffff_fill0_wght400_grad0_opsz48,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                shape = shape,
                isError = ciudadDestinoError,
                textError = R.string.solo_letras,
                fontSize = fontSize,
                fontSizeInput = fontSize,
                modifier = inputModifier
            )

            // fecha
            Box(modifier = inputModifier) {
                EntradaDeTexto(
                    value = fechaInput,
                    onValueChange = {},
                    label = R.string.fecha_label,
                    icon = R.drawable.date_range_48dp_ffffff_fill0_wght400_grad0_opsz48,
                    shape = shape,
                    readOnly = true,
                    fontSize = fontSize,
                    fontSizeInput = fontSize,
                    enabled = true,
                    modifier = inputModifier
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .pointerInput(Unit) { detectTapGestures { showDatePicker = true } }
                )
            }

            // hora
            Box(modifier = inputModifier) {
                EntradaDeTexto(
                    value = horaInput,
                    onValueChange = {},
                    label = R.string.hora_label,
                    icon = R.drawable.schedule_48dp_ffffff_fill0_wght400_grad0_opsz48,
                    shape = shape,
                    fontSize = fontSize,
                    fontSizeInput = fontSize,
                    readOnly = true,
                    enabled = true,
                    modifier = inputModifier
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .pointerInput(Unit) { detectTapGestures { showHourPicker = true } }
                )
            }
        }

        // data picker
        AbrirDatePicker(
            show = showDatePicker,
            onDismiss = { showDatePicker = false },
            onDateSelected = {
                fechaMillis = it
                fechaInput = it.toFormattedDateString()
            }
        )

        // lo mismo time picker
        AbrirTimePicker(
            show = showHourPicker,
            onDismiss = { showHourPicker = false },
            hourSelected = {
                horaMillis = it
                horaInput = it.toFormattedHour()
            }
        )

        Spacer(modifier = Modifier.height(40.dp))

        // aqui se encuentra el crud
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            val btnModifier = Modifier
                .fillMaxWidth()
                .height(58.dp)

            val fontSize = 24.sp

            BotonCustomizable(
                text = R.string.agregar_vuelo_boton,
                onClick = {
                    viewModel.addVuelo(
                        ciudadOrigen,
                        ciudadDestino,
                        fechaMillis,
                        horaMillis
                    ) {
                        ciudadOrigen = ""
                        ciudadDestino = ""
                        fechaInput = ""
                        horaInput = ""
                        refreshNextId()
                    }
                },
                fontSize = fontSize,
                contentColor = White,
                containerColor = DarkRed,
                modifier = btnModifier
            )

            BotonCustomizable(
                text = R.string.traer_vuelo_boton,
                onClick = { dialogMode = DialogMode.BUSCAR },
                fontSize = fontSize,
                contentColor = White,
                containerColor = DarkRed,
                modifier = btnModifier
            )

            BotonCustomizable(
                text = R.string.actualizar_vuelo_boton,
                onClick = { dialogMode = DialogMode.ACTUALIZAR },
                fontSize = fontSize,
                contentColor = White,
                containerColor = DarkRed,
                modifier = btnModifier
            )

            BotonCustomizable(
                text = R.string.eliminar_vuelo_boton,
                onClick = { dialogMode = DialogMode.ELIMINAR },
                fontSize = fontSize,
                contentColor = White,
                containerColor = DarkRed,
                modifier = btnModifier
            )

            Spacer(modifier = Modifier.height(12.dp))

            BotonCustomizable(
                text = R.string.mostrar_vuelos_boton,
                onClick = { dialogMode = DialogMode.MOSTRAR_TODOS },
                fontSize = fontSize,
                contentColor = White,
                containerColor = DarkRed,
                modifier = btnModifier
            )

            BotonCustomizable(
                text = R.string.eliminar_todos_boton,
                onClick = { dialogMode = DialogMode.ELIMINAR_TODOS },
                fontSize = fontSize,
                contentColor = White,
                containerColor = DarkRed,
                modifier = btnModifier
            )
        }

        Spacer(modifier = Modifier.height(30.dp))


        VuelosDialogs(
            dialogMode = dialogMode,
            viewModel = viewModel,
            onDismiss = { dialogMode = DialogMode.NONE },
            ciudadOrigen = ciudadOrigen,
            ciudadDestino = ciudadDestino,
            fechaMillis = fechaMillis,
            horaMillis = horaMillis,
            nextId = nextId,
            onUpdateInputs = { origen, destino, fecha, hora, id ->
                ciudadOrigen = origen
                ciudadDestino = destino
                fechaInput = fecha
                horaInput = hora
                nextId = id
            }
        )
    }
}

