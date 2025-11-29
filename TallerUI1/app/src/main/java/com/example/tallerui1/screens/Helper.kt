package com.example.tallerui1.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@Composable
fun TextFieldInput(
    value: String,
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    @DrawableRes leadingIcon: Int,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(label))},
        leadingIcon = {Icon(painterResource(leadingIcon),null)},
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
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
            style = MaterialTheme.typography.bodyLarge
        )
    }
}