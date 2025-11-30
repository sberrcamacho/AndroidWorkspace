package com.example.appvuelos.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appvuelos.R
import com.example.appvuelos.ui.theme.DarkGray
import com.example.appvuelos.ui.theme.DarkRed
import com.example.appvuelos.ui.theme.White

@Composable
fun PantallaVuelos(
    modifier: Modifier = Modifier,
    toRegresar: (Int) -> Unit
) {
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
            text = stringResource(R.string.vuelos_boton_menu),
            fontSize = 38.sp,
            color = DarkRed,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = stringResource(R.string.id_vuelos_text,"1"),
            fontSize = 22.sp,
            color = DarkGray,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 20.dp, start = 20.dp)
        )

        var showDatePicker by remember { mutableStateOf(false) }
        var showHourPicker by remember { mutableStateOf(false) }

        var fechaInput by remember { mutableStateOf("") }
        var horaInput by remember { mutableStateOf("") }

        Column (
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ){

            val shape = 12.dp
            val fontSize = 22.sp
            val modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)

            var ciudadOrigenInput by remember { mutableStateOf("") }
            var ciudadDestinoInput by remember { mutableStateOf("") }



            EntradaDeTexto(
                value = ciudadOrigenInput,
                onValueChange = { ciudadOrigenInput = it },
                label = R.string.ciudad_de_origen_label,
                icon = R.drawable.flight_takeoff_48dp_ffffff_fill0_wght400_grad0_opsz48,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                shape = shape,
                fontSize = fontSize,
                fontSizeInput = fontSize,
                modifier = modifier
            )
            EntradaDeTexto(
                value = ciudadDestinoInput,
                onValueChange = { ciudadDestinoInput = it },
                label = R.string.ciudad_de_destino_label,
                icon = R.drawable.flight_land_48dp_ffffff_fill0_wght400_grad0_opsz48,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                shape = shape,
                fontSize = fontSize,
                fontSizeInput = fontSize,
                modifier = modifier
            )

            Box(
                modifier = modifier
            ) {
                EntradaDeTexto(
                    value = fechaInput,
                    onValueChange = {},
                    label = R.string.fecha_label,
                    icon = R.drawable.date_range_48dp_ffffff_fill0_wght400_grad0_opsz48,
                    shape = shape,
                    readOnly = true,
                    enabled = true,
                    fontSize = fontSize,
                    fontSizeInput = fontSize,
                    modifier = modifier
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .pointerInput(Unit) {
                            detectTapGestures {
                                showDatePicker = true
                            }
                        }
                )
            }

            Box(
                modifier = modifier
            ) {
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
                    modifier = modifier
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .pointerInput(Unit) {
                            detectTapGestures {
                                showHourPicker = true
                            }
                        }
                )
            }
        }


        AbrirDatePicker(
            show = showDatePicker,
            onDismiss = { showDatePicker = false },
            onDateSelected = { fechaInput = it }
        )

        AbrirTimePicker(
            show = showHourPicker,
            onDismiss = { showHourPicker = false },
            hourSelected = { horaInput = it }
        )

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
                text = R.string.agregar_vuelo_boton,
                onClick = {},
                fontSize = fontSize,
                contentColor = White,
                containerColor = DarkRed,
                modifier = modifier
            )

            BotonCustomizable(
                text = R.string.traer_vuelo_boton,
                onClick = {},
                fontSize = fontSize,
                contentColor = White,
                containerColor = DarkRed,
                modifier = modifier
            )

            BotonCustomizable(
                text = R.string.actualizar_vuelo_boton,
                onClick = {},
                fontSize = fontSize,
                contentColor = White,
                containerColor = DarkRed,
                modifier = modifier
            )

            BotonCustomizable(
                text = R.string.eliminar_vuelo_boton,
                onClick = {},
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

@Preview(showBackground = false)
@Composable
fun preview() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        PantallaVuelos() { }
    }
}
