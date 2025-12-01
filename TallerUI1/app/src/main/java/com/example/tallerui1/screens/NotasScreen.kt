package com.example.tallerui1.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tallerui1.R

@Composable
fun NotesScreen(
    modifier: Modifier = Modifier,
    toRegresar: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .padding(26.dp)
    ) {
        BotonRegresar(
            toRegresar = { toRegresar(0) },
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 20.dp)
        )

        Text(
            text = stringResource(R.string.button_2),
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            maxLines = 2,
            softWrap = true
        )

        Text(
            text = stringResource(R.string.notes_text),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(30.dp))

        var note1Input by remember { mutableStateOf("") }
        var note2Input by remember { mutableStateOf("") }
        var note3Input by remember { mutableStateOf("") }
        var examInput by remember { mutableStateOf("") }
        var projectInput by remember { mutableStateOf("") }

        val note1 = note1Input.toDoubleOrNull()?:0.0
        val note2 = note2Input.toDoubleOrNull()?:0.0
        val note3 = note3Input.toDoubleOrNull()?:0.0
        val exam = examInput.toDoubleOrNull()?:0.0
        val project = projectInput.toDoubleOrNull()?:0.0

        Column (
            verticalArrangement = Arrangement.spacedBy(26.dp)
        ) {
            val keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )

            TextFieldInput(
                value = note1Input,
                onValueChange = {note1Input = it},
                keyboardOptions = keyboardOptions.copy(imeAction = ImeAction.Next),
                label = R.string.nota_1_label,
                leadingIcon = R.drawable.assignment_48dp_ffffff_fill1_wght400_grad0_opsz48
            )

            TextFieldInput(
                value = note2Input,
                onValueChange = {note2Input = it},
                keyboardOptions = keyboardOptions.copy(imeAction = ImeAction.Next),
                label = R.string.nota_2_label,
                leadingIcon = R.drawable.assignment_48dp_ffffff_fill1_wght400_grad0_opsz48
            )

            TextFieldInput(
                value = note3Input,
                onValueChange = {note3Input = it},
                keyboardOptions = keyboardOptions.copy(imeAction = ImeAction.Next),
                label = R.string.nota_3_label,
                leadingIcon = R.drawable.assignment_48dp_ffffff_fill1_wght400_grad0_opsz48
            )

            TextFieldInput(
                value = examInput,
                onValueChange = {examInput = it},
                keyboardOptions = keyboardOptions.copy(imeAction = ImeAction.Next),
                label = R.string.nota_examen_label,
                leadingIcon = R.drawable.quiz_48dp_ffffff_fill1_wght400_grad0_opsz48
            )

            TextFieldInput(
                value = projectInput,
                onValueChange = {projectInput = it},
                keyboardOptions = keyboardOptions.copy(imeAction = ImeAction.Done),
                label = R.string.trabajo_final_label,
                leadingIcon = R.drawable.work_48dp_ffffff_fill1_wght400_grad0_opsz48
            )

        }

        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = stringResource(R.string.nota_final,calcularNotas(
                note1,note2,note3,exam,project
            )),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

private fun calcularNotas(
    nota1: Double,
    nota2: Double,
    nota3: Double,
    examen: Double,
    trabajoFinal: Double
): String {
    val porcentajeParcial = 0.55
    val porcentajeExamen = 0.30
    val porcentajeTrabajoFinal = 0.15

    val parcial = (nota1 + nota2 + nota3) / 3 * porcentajeParcial
    val notaExamen = examen * porcentajeExamen
    val trabajoFinal = trabajoFinal * porcentajeTrabajoFinal

    return String.format("%.2f", parcial + notaExamen + trabajoFinal)
}


