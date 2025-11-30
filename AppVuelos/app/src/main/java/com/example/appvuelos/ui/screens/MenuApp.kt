package com.example.appvuelos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appvuelos.R
import com.example.appvuelos.ui.theme.DarkRed
import com.example.appvuelos.ui.theme.LightGray
import com.example.appvuelos.ui.theme.White

@Composable
fun MenuApp(
    modifier: Modifier = Modifier,
    to: (Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(White)
            .padding(28.dp)
            .wrapContentSize(Alignment.TopCenter)
    ) {
        Spacer(modifier = modifier.height(90.dp))

        Text(
            text = stringResource(R.string.menu_titulo),
            fontSize = 38.sp,
            color = DarkRed,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = modifier.height(80.dp))

        val modifier = Modifier
            .height(82.dp)
            .fillMaxWidth()

        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            BotonCustomizable(
                text = R.string.vuelos_boton_menu,
                onClick = { to(2) },
                fontSize = 32.sp,
                contentColor = Color.Black,
                containerColor = LightGray,
                modifier = modifier
            )

            BotonCustomizable(
                text = R.string.pasajeros_boton_menu,
                onClick = { to(3) },
                fontSize = 32.sp,
                contentColor = Color.Black,
                containerColor = LightGray,
                modifier = modifier
            )

            BotonCustomizable(
                text = R.string.reservas_boton_menu,
                onClick = { to(4) },
                fontSize = 32.sp,
                contentColor = Color.Black,
                containerColor = LightGray,
                modifier = modifier
            )

        }
    }
}



