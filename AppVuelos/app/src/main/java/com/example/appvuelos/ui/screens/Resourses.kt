@file:Suppress("DEPRECATION")

package com.example.appvuelos.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerDialog
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appvuelos.R
import com.example.appvuelos.data.dao.VuelosDao
import com.example.appvuelos.data.entities.VuelosEntity
import com.example.appvuelos.ui.theme.DarkGray
import com.example.appvuelos.ui.theme.DarkRed
import com.example.appvuelos.ui.theme.Orange
import com.example.appvuelos.ui.theme.White
import com.example.appvuelos.ui.viewmodel.VuelosViewModel
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
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
    isError: Boolean = false,
    @StringRes textError: Int = R.string.solo_letras,
    modifier: Modifier = Modifier
) {
    Column {
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
            ),
            isError = isError,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedTextColor = Color.Black,
                errorIndicatorColor = Orange,
                errorLabelColor = Orange,
                errorTextColor = Orange
            ),
            modifier = modifier
        )

        if (isError) {
            Text(
                text = stringResource(textError),
                color = Orange,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun BotonRegresar(
    toRegresar: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = { toRegresar(1) },
        modifier = modifier.scale(0.9f)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null,
            tint = DarkGray,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun AbrirDatePicker(
    show: Boolean,
    onDismiss: () -> Unit,
    onDateSelected: (Long) -> Unit
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
                        onDateSelected(date)
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
    hourSelected: (Long) -> Unit
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

                        val millis = LocalTime.of(hour, minute)
                            .atDate(LocalDate.of(1970, 1, 1)) // es la fecha base para programas
                            .atZone(ZoneId.systemDefault())
                            .toInstant()
                            .toEpochMilli()

                        hourSelected(millis)
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



fun Long.toFormattedDateString(): String {
    val localDate = Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()

    val formatter = DateTimeFormatter.ofPattern(
        "MMMM d, yyyy",
        Locale("es")
    )

    val formatted = localDate.format(formatter)
    return formatted.replaceFirstChar { it.uppercase() }
}

fun Long.toFormattedHour(): String {
    val time = Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .toLocalTime()

    val formatter = DateTimeFormatter.ofPattern("hh:mm a")
    return time.format(formatter)
}

fun Long.toFormattedShortDateString(): String {
    val localDate = Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()

    val formatter = DateTimeFormatter.ofPattern("dd/MM/yy", Locale("es"))
    return localDate.format(formatter)
}

fun Long.toFormattedShortHour(): String {
    val localTime = Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .toLocalTime()

    val formatter = DateTimeFormatter.ofPattern("HH:mm") // 24h
    return localTime.format(formatter)
}


fun String.toValidNameOrUnknown(): String {
    val trimmed = this.trim()

    return if (trimmed.isNotEmpty() && trimmed.all { it.isLetter() }) {
        trimmed
    } else {
        "Unknown"
    }
}


@Composable
fun CampoID(
    label: String,
    icon: Int,
    modifier: Modifier = Modifier
) {
    // Colores iguales a tus OutlinedTextField
    val colors = TextFieldDefaults.colors(
        unfocusedContainerColor = Color(0xFFE5E5E5),
        focusedContainerColor = Color(0xFFE5E5E5),
        disabledContainerColor = Color(0xFFE5E5E5),
        disabledTextColor = Color.Black,
    )

    OutlinedTextField(
        value = label,
        onValueChange = {},
        readOnly = true,
        enabled = false,
        leadingIcon = { Icon(painterResource(icon), null, modifier = Modifier.scale(0.9F)) },
        placeholder = null,
        shape = RoundedCornerShape(24.dp),
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = Color.Black
        ),
        colors = colors,
        modifier = modifier.height(50.dp)
    )
}



















