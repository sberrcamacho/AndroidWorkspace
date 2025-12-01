package com.example.tallerui1.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.tallerui1.R

@Composable
fun AdivinaScreen(
    modifier: Modifier = Modifier,
    toRegresar: (Int) -> Unit
) {
    // Estado del número a adivinar
    val numeroAdivinar = remember { (1..100).random() }
    var intentos by remember { mutableIntStateOf(0) }
    var mensaje by remember { mutableStateOf("Tienes 10 intentos para adivinar.") }
    var input by remember { mutableStateOf("") }

    var isErrorInput by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .wrapContentSize()
            .padding(26.dp),
    ) {
        BotonRegresar(
            toRegresar = { toRegresar(0) },
            modifier = Modifier
                .align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Adivina el número",
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth(),
            maxLines = 2
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Aquí juegas a adivinar un número entre 1 y 100 en máximo 10 intentos\n\nIntenta descubrir el número secreto.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(70.dp))

        TextFieldInput(
            value = input,
            onValueChange = {
                input = it
                val n = it.toIntOrNull()
                isErrorInput = n == null || n !in 1..100
            },
            label =  R.string.adivinar_numero_text,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            isError = isErrorInput,
            errorText = R.string.error_rango,
            leadingIcon = R.drawable.question_mark_48dp_ffffff_fill0_wght400_grad0_opsz48
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val n = input.toIntOrNull()
                if (n != null && intentos < 10 && !isErrorInput ) {
                    intentos++
                    mensaje = if (n == numeroAdivinar) {
                        "Has adivinado en $intentos intentos"
                    } else if (intentos == 10) {
                        "No acertaste. El número era $numeroAdivinar"
                    } else {
                        if (n < numeroAdivinar) {
                            "El número es mayor. Intento $intentos/10"
                        } else {
                            "El número es menor. Intento $intentos/10"
                        }
                    }
                }
                input = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Probar")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = mensaje,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}