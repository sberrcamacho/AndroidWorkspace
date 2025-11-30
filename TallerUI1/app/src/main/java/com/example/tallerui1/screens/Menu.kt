package com.example.tallerui1.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tallerui1.R

@Composable
fun MenuApp(
    modifier: Modifier = Modifier,
    to: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .padding(26.dp)
    ) {
        Text(
            text = stringResource(R.string.menu_title),
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
            text = stringResource(R.string.text_menu),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(100.dp))

        // Manage the buttons
        Column (
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ){
            val modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)


            CustomButton(
                text = R.string.button_1,
                onClick = { to(1) },
                modifier = modifier
            )

            CustomButton(
                text = R.string.button_2,
                onClick = { to(2) },
                modifier = modifier
            )

            CustomButton(
                text = R.string.button_3,
                onClick = { to(3) },
                modifier = modifier
            )

            CustomButton(
                text = R.string.button_4,
                onClick = { to(4) },
                modifier = modifier
            )

            CustomButton(
                text = R.string.button_5,
                onClick = { to(5) },
                modifier = modifier
            )
        }
    }
}



