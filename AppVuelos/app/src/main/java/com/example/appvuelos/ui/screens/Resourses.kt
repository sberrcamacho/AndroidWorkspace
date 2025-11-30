package com.example.appvuelos.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerDialog
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appvuelos.R
import com.example.appvuelos.ui.theme.DarkGray
import com.example.appvuelos.ui.theme.DarkRed
import com.example.appvuelos.ui.theme.White
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun BotonCustomizable(
    @StringRes text: Int,
    onClick: () -> Unit,
    fontSize: TextUnit,
    contentColor: Color,
    containerColor: Color,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) { Text(stringResource(text), fontSize = fontSize) }
}

@Composable
fun EntradaDeTexto(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int,
    @DrawableRes icon: Int,
    fontSize: TextUnit,
    fontSizeInput: TextUnit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Dp,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        leadingIcon = { Icon(painterResource(icon), null, modifier = Modifier.scale(1.4F)) },
        placeholder = { Text(text = stringResource(label), fontSize = fontSize) },
        keyboardOptions = keyboardOptions,
        singleLine = true,
        shape = RoundedCornerShape(shape),
        textStyle = TextStyle(
            fontSize = fontSizeInput,
            color = Color.Black
        ),
        modifier = modifier
    )
}

@Composable
fun BotonRegresar(
    toRegresar: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Button (
            onClick = { toRegresar(1) },
            modifier = modifier
                .scale(0.8F),
            colors = ButtonDefaults.buttonColors(
                contentColor = White,
                containerColor = DarkRed
            )
        ) {
        Row {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                tint = White,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = stringResource(R.string.regresar_boton),
                color = White,
                fontSize = 22.sp
            )
        }
        }
}

@Composable
fun AbrirDatePicker(
    show: Boolean,
    onDismiss: () -> Unit,
    onDateSelected: (String) -> Unit
) {
    if (!show) return

    val dataPickerState = rememberDatePickerState()

    val fontSize = 14.sp
    val contentColor = White
    val containerColor = DarkRed

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            BotonCustomizable(
                text = R.string.confirmar_boton_datapicker,
                onClick = {
                    val date = dataPickerState.selectedDateMillis
                    date?.let {
                        val localDate = Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()

                        val day = localDate.dayOfMonth
                        val month = localDate.format(DateTimeFormatter.ofPattern("MMMM",
                            Locale("es")
                        ))
                            .replaceFirstChar { char -> char.uppercase() }
                        val year = localDate.year

                        val formatDate = "$month $day, $year"
                        onDateSelected(formatDate)
                    }

                    onDismiss()
                },
                fontSize = fontSize,
                contentColor = contentColor,
                containerColor = containerColor
            )
        },
        dismissButton = {
            BotonCustomizable(
                text = R.string.cancelar_boton_datapicker,
                onClick = { onDismiss() },
                fontSize = fontSize,
                contentColor = contentColor,
                containerColor = containerColor
            )
        }
    ) {
        DatePicker(
            state = dataPickerState,
            colors = DatePickerDefaults.colors(
                selectedDayContainerColor = DarkRed,
                todayContentColor = DarkRed,
                todayDateBorderColor = DarkRed,
                selectedYearContainerColor = DarkRed,
            )
        )
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AbrirTimePicker(
    show: Boolean,
    onDismiss: () -> Unit,
    hourSelected: (String) -> Unit
) {
    if (!show) return

    val dataTime = LocalDateTime.now()

    val timePickerState = rememberTimePickerState(
        is24Hour = false,
        initialHour = dataTime.hour,
        initialMinute = dataTime.minute
    )
    
    val fontSize = 14.sp
    val contentColor = White
    val containerColor = DarkRed


    TimePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            BotonCustomizable(
                    text = R.string.confirmar_boton_datapicker,
                    onClick = {
                        val hour = timePickerState.hour
                        val minute = timePickerState.minute

                        val period = if (hour < 12) "AM" else "PM"

                        val hour12 = when {
                            hour == 0 -> 12
                            hour > 12 -> hour - 12
                            else -> hour
                        }

                        val formattedHour = hour12.toString().padStart(2, '0')
                        val formattedMinute = minute.toString().padStart(2, '0')

                        val selected = "$formattedHour:$formattedMinute $period"

                        hourSelected(selected)
                        onDismiss()
                    },
                    fontSize = fontSize,
                    contentColor = contentColor,
                    containerColor = containerColor
                )
        },
        dismissButton = {
            BotonCustomizable(
                text = R.string.cancelar_boton_datapicker,
                onClick = { onDismiss() },
                fontSize = fontSize,
                contentColor = contentColor,
                containerColor = containerColor,
                modifier = Modifier.padding(end = 10.dp)
            )
        },
        title = {}
    ) {
        TimePicker(
            state = timePickerState,
            colors = TimePickerDefaults.colors(
                selectorColor = DarkRed,
                timeSelectorSelectedContainerColor = DarkRed,
                periodSelectorSelectedContainerColor = DarkRed,
                periodSelectorSelectedContentColor = Color.White,
                timeSelectorSelectedContentColor = Color.White
            )
        )
    }
}














