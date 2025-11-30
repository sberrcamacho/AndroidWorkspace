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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appvuelos.R
import com.example.appvuelos.data.database.DatabaseProvider
import com.example.appvuelos.ui.theme.DarkRed
import com.example.appvuelos.ui.theme.White
import com.example.appvuelos.ui.viewmodels.PasajeroViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appvuelos.data.entities.PasajeroEntity
import com.example.appvuelos.ui.theme.DarkGray
import com.example.appvuelos.ui.viewmodels.PasajeroViewModelFactory


@Composable
fun PantallaPasajeros(
    modifier: Modifier = Modifier,
    toRegresar: (Int) -> Unit
) {
    val context = LocalContext.current
    val database = DatabaseProvider.getDatabase(context)
    val dao = database.pasajeroDao()

    val viewModel: PasajeroViewModel = viewModel(
        factory = PasajeroViewModelFactory(dao)
    )


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(White)
            .padding(28.dp)
            .wrapContentSize(Alignment.TopCenter)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = modifier.height(46.dp))

        Text(
            text = stringResource(R.string.pasajeros_boton_menu),
            fontSize = 38.sp,
            color = DarkRed,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = stringResource(R.string.id_vuelos_text),
            fontSize = 22.sp,
            color = DarkGray,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 20.dp, start = 20.dp)
        )

        val encontrado by viewModel.pasajeroEncontrado

        var nombreInput by remember { mutableStateOf("") }
        var apellidoInput by remember { mutableStateOf("") }
        var documentoInput by remember { mutableStateOf("") }
        var telefonoInput by remember { mutableStateOf("") }

        LaunchedEffect(encontrado) {
        if (encontrado != null) {
            nombreInput = encontrado!!.nombre
            apellidoInput = encontrado!!.apellido
            documentoInput = encontrado!!.documento
            telefonoInput = encontrado!!.telefono
        }
    }

        Column (
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ){
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
                onValueChange = { nombreInput = it },
                label = R.string.nombre_label,
                icon = R.drawable.person_48dp_ffffff_fill0_wght400_grad0_opsz48,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                shape = shape,
                fontSize = fontSize,
                fontSizeInput = fontSize,
                modifier = modifier
            )
            EntradaDeTexto(
                value = apellidoInput,
                onValueChange = { apellidoInput = it },
                label = R.string.apellido_label,
                icon = R.drawable.group_48dp_ffffff_fill0_wght400_grad0_opsz48,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                shape = shape,
                fontSize = fontSize,
                fontSizeInput = fontSize,
                modifier = modifier
            )
            EntradaDeTexto(
                value = documentoInput,
                onValueChange = { documentoInput = it },
                label = R.string.documento_label,
                icon = R.drawable.id_card_48dp_ffffff_fill0_wght400_grad0_opsz48,
                keyboardOptions = keyboardOptions.copy(imeAction = ImeAction.Next),
                shape = shape,
                fontSize = fontSize,
                fontSizeInput = fontSize,
                modifier = modifier
            )
            EntradaDeTexto(
                value = telefonoInput,
                onValueChange = { telefonoInput = it },
                label = R.string.telefono_label,
                icon = R.drawable.call_48dp_ffffff_fill1_wght400_grad0_opsz48,
                keyboardOptions = keyboardOptions.copy(imeAction = ImeAction.Done),
                shape = shape,
                fontSize = fontSize,
                fontSizeInput = fontSize,
                modifier = modifier
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            val modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)

            val fontSize = 24.sp

            BotonCustomizable(
                text = R.string.agregar_pasajero,
                onClick = {
                    viewModel.agregar(nombreInput, apellidoInput, documentoInput, telefonoInput)
                },
                fontSize = fontSize,
                contentColor = White,
                containerColor = DarkRed,
                modifier = modifier
            )

            BotonCustomizable(
                text = R.string.buscar_pasajero,
                onClick = {viewModel.leer(documentoInput)},
                fontSize = fontSize,
                contentColor = White,
                containerColor = DarkRed,
                modifier = modifier
            )

            BotonCustomizable(
                text = R.string.actualizar_pasajero,
                onClick = {
                    viewModel.actualizar(nombreInput, apellidoInput, documentoInput, telefonoInput)
                },
                fontSize = fontSize,
                contentColor = White,
                containerColor = DarkRed,
                modifier = modifier
            )

            BotonCustomizable(
                text = R.string.eliminar_pasajero,
                onClick = {
                    viewModel.eliminar(documentoInput)
                    nombreInput = ""
                    apellidoInput = ""
                    documentoInput = ""
                    telefonoInput = ""
                },
                fontSize = fontSize,
                contentColor = White,
                containerColor = DarkRed,
                modifier = modifier
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        BotonRegresar(
            toRegresar = { toRegresar(1) },
            modifier = Modifier.align(Alignment.Start)
        )
    }
}


