package com.example.tallerui1.screens

import android.icu.text.NumberFormat
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
import java.util.Locale

@Composable
fun SalarioScreen(
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
            text = stringResource(R.string.button_3),
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
        )

        Text(
            text = stringResource(R.string.text_salario_screen),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(50.dp))

        var codigoInput by remember { mutableStateOf("") }
        var diasInput by remember { mutableStateOf("") }
        var salarioInput by remember { mutableStateOf("") }

        val dias = diasInput.toIntOrNull()?:0
        val salario = salarioInput.toDoubleOrNull()?:0.0

        Column (
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            val keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )

            TextFieldInput(
                value = codigoInput,
                label = R.string.codigo_label,
                onValueChange = { codigoInput = it },
                keyboardOptions = keyboardOptions.copy(imeAction = ImeAction.Next),
                leadingIcon = R.drawable.money_48dp_ffffff_fill1_wght400_grad0_opsz48
            )

            TextFieldInput(
                value = diasInput,
                label = R.string.dias_label,
                onValueChange = { diasInput = it },
                keyboardOptions = keyboardOptions.copy(imeAction = ImeAction.Done),
                leadingIcon = R.drawable.attach_money_48dp_ffffff_fill1_wght400_grad0_opsz48
            )

            TextFieldInput(
                value = salarioInput,
                label = R.string.salario_label,
                onValueChange = { salarioInput = it },
                keyboardOptions = keyboardOptions.copy(imeAction = ImeAction.Done),
                leadingIcon = R.drawable.attach_money_48dp_ffffff_fill1_wght400_grad0_opsz48
            )

        }

        Spacer(modifier = Modifier.height(40.dp))


        val salarioNeto = calcularSalarioNeto(dias, salario)
        Text(
            text = stringResource(R.string.resultado_codigo_salario,codigoInput),
            style = MaterialTheme.typography.titleMedium
        )

        val formatter = NumberFormat.getCurrencyInstance(Locale("es", "CO"))
        formatter.maximumFractionDigits = 0
        val salarioFormateado = formatter.format(salarioNeto)

        Text(
            text = stringResource(R.string.resultado_screen_salario,salarioFormateado),
            style = MaterialTheme.typography.bodyLarge
        )


    }
}

private fun calcularSalarioNeto(numeroDias: Int, salario: Double): Double {
    val salarioMinimo = 1_400_000

    var sameSalario = salario

    sameSalario *= numeroDias

    var impuestos: Double
    var seguroSocial: Double
    var pensiones: Double
    var subsidio: Double

    val salarioMensualNeto = if (salario >= salarioMinimo) {
        impuestos = 0.07
        seguroSocial = 0.03
        pensiones = 0.02

        sameSalario - sameSalario * (impuestos + seguroSocial + pensiones)
    } else {
        seguroSocial = 0.02
        pensiones = 0.015
        subsidio = 0.02

        sameSalario - sameSalario * (subsidio + seguroSocial + pensiones)
    }

    return salarioMensualNeto

}