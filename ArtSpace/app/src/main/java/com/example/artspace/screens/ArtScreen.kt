package com.example.artspace.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.R
import com.example.artspace.ui.theme.ArtSpaceTheme
import com.example.artspace.ui.theme.Periwinkle


@Composable
fun ArtScreen(
    id: Int,
    @DrawableRes image: Int,
    @StringRes title: Int,
    @StringRes author: Int,
    @StringRes year: Int,
    onPrevious: (Int) -> Unit,
    onNext: (Int) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .wrapContentSize(Alignment.BottomCenter)
    ) {
        Spacer(Modifier.weight(1F/3F))

        Box(
            modifier = Modifier
                .aspectRatio(7F / 9F)
                .weight(12F)
                .background(Color.White)
                .shadow(elevation = 3.dp)
                .padding(40.dp)
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        Spacer(Modifier.weight(1F/2F))

        Box(
            modifier = Modifier
                .background(Periwinkle)

        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .wrapContentSize(Alignment.CenterStart)
            ) {
                Text(
                    text = stringResource(title),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Light
                )

                Row {
                    Text(
                        text = stringResource(author),
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = " (${stringResource(year)})",
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }
        Spacer(Modifier.weight(1F))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp, end = 16.dp
                )
        ) {
            Button(
                onClick = { onPrevious(id - 1) },
                modifier = Modifier.width(138.dp)
            ) {
                Text(text = "Previous")
            }


            Button(
                onClick = { onNext(id + 1) },
                modifier = Modifier.width(138.dp)
            ) {
                Text(text = "Next")
            }
        }
    }
}
