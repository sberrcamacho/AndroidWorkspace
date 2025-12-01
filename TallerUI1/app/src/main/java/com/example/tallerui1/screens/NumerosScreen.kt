import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import com.example.tallerui1.R
import com.example.tallerui1.screens.BotonRegresar
import com.example.tallerui1.screens.TextFieldInput
import com.example.tallerui1.screens.filterElements
import com.example.tallerui1.screens.generarLista
import com.example.tallerui1.screens.multiplesOfSeven
import com.example.tallerui1.screens.sumOfEven

@Composable
fun NumerosScreen(
    modifier: Modifier = Modifier,
    toRegresar: (Int) -> Unit
) {
    var rangeInput by remember { mutableStateOf("") }
    var isErrorInput by remember { mutableStateOf(false) }
    var resultados by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .wrapContentSize()
            .padding(26.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        BotonRegresar(
            toRegresar = { toRegresar(0) },
            modifier = Modifier.align(Alignment.Start)
        )

        Text(
            text = stringResource(R.string.button_5),
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )

        Text(
            text = "Ingresa un número mayor a 144 para generar la lista y ver resultados.",
            style = MaterialTheme.typography.bodyLarge
        )

        TextFieldInput(
            value = rangeInput,
            onValueChange = {
                rangeInput = it
                val n = it.toIntOrNull()
                isErrorInput = n == null || n <= 144
            },
            label = R.string.ingresa_rango,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            isError = isErrorInput,
            errorText = R.string.error_rango_mayor_144,
            leadingIcon = R.drawable.numbers_48dp_ffffff_fill0_wght400_grad0_opsz48
        )

        Button(
            onClick = {
                val range = rangeInput.toIntOrNull()
                if (range != null && range > 144) {
                    val newList = generarLista(range)
                    val max = newList.maxOrNull()
                    val resultadosTexto = buildString {
                        appendLine("Lista generada: $newList")
                        appendLine()
                        appendLine("Número mayor: $max en índice ${newList.indexOf(max)}")
                        appendLine("Suma de posiciones pares: ${sumOfEven(newList)}")
                        appendLine("Elementos entre 81 y 119: ${filterElements(newList)}")
                        appendLine("Múltiplos de 7: ${multiplesOfSeven(newList)}")
                        appendLine()
                        appendLine("Lista inversa: ${newList.reversed()}")
                    }
                    resultados = resultadosTexto
                    isErrorInput = false
                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Generar")
        }

        if (resultados.isNotEmpty()) {
            Text(
                text = resultados,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


