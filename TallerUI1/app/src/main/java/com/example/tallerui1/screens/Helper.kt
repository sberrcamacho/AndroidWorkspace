package com.example.tallerui1.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tallerui1.R
import com.example.tallerui1.ui.theme.DarkGray
import com.example.tallerui1.ui.theme.Orange
import kotlin.random.Random

@Composable
fun TextFieldInput(
    value: String,
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    @DrawableRes leadingIcon: Int,
    isError: Boolean = false,
    onValueChange: (String) -> Unit,
    @StringRes errorText: Int = R.string.error_solo_numeros,
    modifier: Modifier = Modifier
) {
    Column {
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(stringResource(label)) },
            leadingIcon = { Icon(painterResource(leadingIcon), null) },
            keyboardOptions = keyboardOptions,
            isError = isError,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Black,
                errorIndicatorColor = Orange,
                errorTextColor = Orange,
                errorLabelColor = Orange
            ),
            modifier = modifier
        )

        if (isError) {
            Text(
                text = stringResource(errorText),
                style = MaterialTheme.typography.bodyLarge,
                color = Orange
            )
        }
    }
}

@Composable
fun CustomButton(
    @StringRes text: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.titleMedium
        )
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

fun generarLista(range: Int): List<Int> {
    val newList = mutableListOf<Int>()
    repeat(144) {
        var n: Int
        do {
            n = Random.nextInt(1, range + 1)
        } while (n in newList)
        newList.add(n)
    }
    return newList
}

fun sumOfEven(myList: List<Int>): Int {
    return myList.filterIndexed { index, _ -> index % 2 == 0 }.sum()
}

fun filterElements(myList: List<Int>): Int {
    return myList.count { it in 81 until 120 }
}

fun multiplesOfSeven(myList: List<Int>): Int {
    return myList.count { it % 7 == 0 }
}
