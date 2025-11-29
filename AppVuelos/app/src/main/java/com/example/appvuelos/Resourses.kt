package com.example.appvuelos

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appvuelos.ui.theme.DarkGray
import com.example.appvuelos.ui.theme.DarkRed
import com.example.appvuelos.ui.theme.Periwinkle
import com.example.appvuelos.ui.theme.White

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
    keyboardOptions: KeyboardOptions,
    shape: Dp,
    readOnly: Boolean = false,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = { Icon(painterResource(icon), null, modifier = Modifier.scale(1.4F)) },
        placeholder = { Text(text = stringResource(label), fontSize = fontSize) },
        keyboardOptions = keyboardOptions,
        singleLine = true,
        shape = RoundedCornerShape(shape),
        textStyle = TextStyle(
            fontSize = fontSizeInput,
        ),
        readOnly = readOnly,
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
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.regresar_boton),
                tint = White,
                modifier = Modifier.size(24.dp)
            )
        }
}

@Composable
fun EntradaCalendario(
    text: String,
    fontSize: TextUnit,
    contentColor: Color,
    containerColor: Color,
    shape: Dp,
    scaleIcon: Float,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }
    val state = rememberDatePickerState()


    Button(
        onClick = {showDialog = true},
        modifier = modifier,
        shape = RoundedCornerShape(shape),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
        ) {
            Icon(
                painter = painterResource(R.drawable.date_range_48dp_ffffff_fill0_wght400_grad0_opsz48),
                contentDescription = null,
                modifier = Modifier.scale(scaleIcon)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = text,
                fontSize = fontSize,
                fontWeight = FontWeight.W400
            )
        }
    }

    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {}
        ) {
            DatePicker(state = state)
        }
    }
}

    // Date field where the user can see the selected date













