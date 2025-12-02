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
import com.example.appvuelos.ui.dialogs.DialogMode
import com.example.appvuelos.ui.dialogs.PasajerosDialogs
import com.example.appvuelos.ui.theme.DarkRed
import com.example.appvuelos.ui.theme.White
import com.example.appvuelos.ui.viewmodel.PasajerosViewModel

@Composable
fun PantallaPasajeros(
    modifier: Modifier = Modifier, toRegresar: (Int) -> Unit
) {
    // Conexión directa al DAO
    val viewModel = remember { PasajerosViewModel(RoomApplication.db.pasajerosDao()) }

    // estados
    var dialogMode by remember { mutableStateOf(DialogMode.NONE) }

    var nombreInput by remember { mutableStateOf("") }
    var apellidoInput by remember { mutableStateOf("") }
    var documentoInput by remember { mutableStateOf("") }
    var telefonoInput by remember { mutableStateOf("") }

    val nombreValid = nombreInput.toValidNameOrNull()
    val apellidoValid = apellidoInput.toValidNameOrNull()
    val documento = documentoInput.toIntOrNull()
    val telefono = telefonoInput.toIntOrNull()

    var nombreError by remember { mutableStateOf(false) }
    var apellidoError by remember { mutableStateOf(false) }
    var documentoError by remember { mutableStateOf(false) }
    var telefonoError by remember { mutableStateOf(false) }

    var nextId by remember { mutableIntStateOf(1) }


    fun refreshNextId() {
        viewModel.getNextPasajeroId { id -> nextId = id }
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
            toRegresar = { toRegresar(it) },
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 20.dp)
        )

        Text(
            text = "Gestión de Pasajeros", style = MaterialTheme.typography.displaySmall.copy(
                color = DarkRed, fontWeight = FontWeight.Bold, fontSize = 31.sp
            ), modifier = Modifier.align(Alignment.Start)
        )

        Text(
            text = "En esta sección podrá registrar, consultar, actualizar y eliminar la información de los pasajeros vinculados a sus vuelos. \n" + "Cada registro debe contener los datos básicos de identificación y contacto, garantizando la correcta administración de las reservas. \n" + "El sistema valida automáticamente la información ingresada para asegurar la integridad de la base de datos y facilitar la gestión de sus operaciones.",
            style = MaterialTheme.typography.bodyLarge.copy(),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 20.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        CampoID(
            label = stringResource(R.string.id_vuelos_text, nextId),
            icon = R.drawable.id_card_48dp_ffffff_fill0_wght400_grad0_opsz48,
            modifier = Modifier
                .align(Alignment.Start)
                .fillMaxWidth(0.5F)
                .padding(bottom = 20.dp, start = 20.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            val keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )

            val shape = 12.dp
            val fontSize = 22.sp
            val modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)


            EntradaDeTexto(
                value = nombreInput,
                onValueChange = {
                    nombreInput = it; nombreError =
                    it.isBlank() || !it.all { char -> char.isLetter() }
                },
                label = R.string.nombre_label,
                icon = R.drawable.person_48dp_ffffff_fill0_wght400_grad0_opsz48,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                shape = shape,
                fontSize = fontSize,
                fontSizeInput = fontSize,
                isError = nombreError,
                textError = R.string.solo_letras,
                modifier = modifier
            )

            EntradaDeTexto(
                value = apellidoInput,
                onValueChange = {
                    apellidoInput = it; apellidoError =
                    it.isBlank() || !it.all { char -> char.isLetter() }
                },
                label = R.string.apellido_label,
                icon = R.drawable.group_48dp_ffffff_fill0_wght400_grad0_opsz48,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                shape = shape,
                fontSize = fontSize,
                fontSizeInput = fontSize,
                isError = apellidoError,
                textError = R.string.solo_letras,
                modifier = modifier
            )

            EntradaDeTexto(
                value = documentoInput,
                onValueChange = {
                    documentoInput = it; documentoError =
                    it.isBlank() || !it.all { char -> char.isDigit() }
                },
                label = R.string.documento_label,
                icon = R.drawable.id_card_48dp_ffffff_fill0_wght400_grad0_opsz48,
                keyboardOptions = keyboardOptions.copy(imeAction = ImeAction.Next),
                shape = shape,
                fontSize = fontSize,
                fontSizeInput = fontSize,
                isError = documentoError,
                textError = R.string.solo_numeros,
                modifier = modifier
            )
            EntradaDeTexto(
                value = telefonoInput,
                onValueChange = {
                    telefonoInput = it; telefonoError =
                    it.isBlank() || !it.all { char -> char.isDigit() }
                },
                label = R.string.telefono_label,
                icon = R.drawable.call_48dp_ffffff_fill1_wght400_grad0_opsz48,
                keyboardOptions = keyboardOptions.copy(imeAction = ImeAction.Done),
                shape = shape,
                fontSize = fontSize,
                isError = telefonoError,
                textError = R.string.solo_numeros,
                fontSizeInput = fontSize,
                modifier = modifier
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // botones
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)


            BotonCustomizable(
                text = R.string.agregar_pasajero,
                onClick = {
                    viewModel.addPasajero(
                        nombre = nombreValid,
                        apellido = apellidoValid,
                        telefono = telefono,
                        documento = documento
                    ) {
                        refreshNextId()
                        nombreInput = ""
                        apellidoInput = ""
                        documentoInput = ""
                        telefonoInput = ""
                    }
                },
                modifier = modifier
            )

            BotonCustomizable(
                text = R.string.buscar_pasajero,
                onClick = { dialogMode = DialogMode.BUSCAR },
                modifier = modifier
            )

            BotonCustomizable(
                text = R.string.actualizar_pasajero,
                onClick = { dialogMode = DialogMode.ACTUALIZAR },
                modifier = modifier
            )

            BotonCustomizable(
                text = R.string.eliminar_pasajero,
                onClick = { dialogMode = DialogMode.ELIMINAR },
                modifier = modifier
            )

            Spacer(modifier = Modifier.height(12.dp))

            BotonCustomizable(
                text = R.string.mostrar_todos_pasajeros,
                onClick = { dialogMode = DialogMode.MOSTRAR_TODOS },
                modifier = modifier
            )

            BotonCustomizable(
                text = R.string.eliminar_todos_pasajeros,
                onClick = { dialogMode = DialogMode.ELIMINAR_TODOS },
                modifier = modifier
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        PasajerosDialogs(
            dialogMode = dialogMode,
            viewModel = viewModel,
            nombreInput = nombreValid,
            apellidoInput = apellidoValid,
            documento = documento,
            telefono = telefono,
            nextId = nextId,
            onDismiss = { dialogMode = DialogMode.NONE },
            onUpdateInputs = { nombre, apellido, doc, tel, id ->
                nombreInput = nombre
                apellidoInput = apellido
                documentoInput = doc
                telefonoInput = tel
                nextId = id
            }
        )
    }
}









