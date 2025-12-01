package com.example.tallerui1.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
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
import java.text.NumberFormat

@Composable
fun VentasScreen(
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
            text = stringResource(R.string.button_1),
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            maxLines = 2
        )

        Text(
            text = stringResource(R.string.sales_text_1),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(70.dp))

        Text(
            text = "Digite su información:",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        var salaryInput by remember { mutableStateOf("") }
        var salesInput by remember { mutableStateOf("") }

        val salary = salaryInput.toDoubleOrNull()?:0.0
        val sales = salesInput.toIntOrNull()?:0


        Column (
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            val keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )

            TextFieldInput(
                value = salaryInput,
                label = R.string.salario_label,
                onValueChange = {salaryInput = it},
                keyboardOptions = keyboardOptions.copy(imeAction = ImeAction.Next),
                leadingIcon = R.drawable.money_48dp_ffffff_fill1_wght400_grad0_opsz48
            )

            TextFieldInput(
                value = salesInput,
                label = R.string.ventas_al_mes_label,
                onValueChange = {salesInput = it},
                keyboardOptions = keyboardOptions.copy(imeAction = ImeAction.Done),
                leadingIcon = R.drawable.attach_money_48dp_ffffff_fill1_wght400_grad0_opsz48
            )

        }
        val results = commissionCalculation(salary, sales)

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(R.string.sales_text_2,results[0],results[1]),
            style = MaterialTheme.typography.bodyLarge
        )
        

    }
}

private fun commissionCalculation(salary: Double, sales: Int): List<String> {
    val percentOfCommissions = 0.10
    val commissionsInAMonth = salary * percentOfCommissions * sales
    val totalInTheMonth = salary + commissionsInAMonth

    return listOf(
        NumberFormat.getCurrencyInstance().format(commissionsInAMonth),
        NumberFormat.getCurrencyInstance().format(totalInTheMonth)
    )
}
