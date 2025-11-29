package com.example.tallerui1.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.tallerui1.R

@Composable
fun NotesScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .padding(30.dp)
    ) {
        Text(
            text = stringResource(R.string.button_2),
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            maxLines = 2,
            softWrap = true
        )

        Text(
            text = "",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(70.dp))

        Text(
            text = "",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        var note1Input by remember { mutableStateOf("") }
        var note2Input by remember { mutableStateOf("") }
        var note3Input by remember { mutableStateOf("") }

        val note1 = note1Input.toDoubleOrNull()?:0.0
        val note2 = note2Input.toDoubleOrNull()?:0.0
        val note3 = note3Input.toDoubleOrNull()?:0.0

        Column (
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            val keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )

            TextFieldInput(
                value = note1Input,
                onValueChange = {note1Input = it},
                keyboardOptions = keyboardOptions.copy(imeAction = ImeAction.Next),
                label = R.string.nota_1_label,
                leadingIcon = Icons.Default.Done
            )

        }

        Spacer(modifier = Modifier.height(30.dp))


    }
}

//const val PORCENTAJE_PARCIAL = 0.55
//const val PORCENTAJE_EXAMEN = 0.30
//const val PORCENTAJE_TRABAJO_FINAL = 0.15
//
//fun main() {
//    val nota1 = 3.5
//    val nota2 = 5.0
//    val nota3 = 2.0
//    val parcial = (nota1 + nota2 + nota3) / 3 * PORCENTAJE_PARCIAL
//    val notaExamen = 4.0 * PORCENTAJE_EXAMEN
//    val trabajoFinal = 1.0 * PORCENTAJE_TRABAJO_FINAL
//    val notaFinal = parcial + notaExamen + trabajoFinal
//
//    println("La nota final del curso es de $notaFinal")
//}
