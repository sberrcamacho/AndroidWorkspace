package com.example.appvuelos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appvuelos.R
import com.example.appvuelos.ui.theme.DarkRed
import com.example.appvuelos.ui.theme.OtherWhite
import com.example.appvuelos.ui.theme.White

@Composable
fun PantallaPrincipal(
    modifier: Modifier = Modifier.background(OtherWhite),
    to: (Int) -> Unit
) {
    Box (modifier = modifier){
        Image (
            painter = painterResource(R.drawable.pigeon_airlines),
            contentDescription = null,
            modifier = modifier.fillMaxSize()
        )

        Text(
            text = "\uD835\uDC77\uD835\uDC8A\uD835\uDC88\uD835\uDC86\uD835\uDC90\uD835\uDC8F\n" +
                    "      \uD835\uDC68\uD835\uDC8A\uD835\uDC93\uD835\uDC8D\uD835\uDC8A\uD835\uDC8F\uD835\uDC86\uD835\uDC94",
            style = MaterialTheme.typography.displayLarge.copy(
                color = DarkRed,
                fontWeight = FontWeight.Bold
            ),
            modifier = modifier.align(Alignment.TopCenter).padding(top = 50.dp)
        )


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(28.dp)
                .align(Alignment.BottomCenter)
        ) {

            val modifier = Modifier
                .height(86.dp)
                .fillMaxWidth()


            BotonCustomizable(
                text = R.string.entrar_boton,
                onClick = { to(1) },
                fontSize = 32.sp,
                modifier = modifier
            )

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}



