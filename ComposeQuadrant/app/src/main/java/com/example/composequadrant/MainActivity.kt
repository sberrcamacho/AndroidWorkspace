package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeQuadrantTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PrincipalFrame()
                }
            }
        }
    }
}

@Composable
fun PrincipalFrame(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = modifier
                .weight(1F)
                .fillMaxSize()
        ) {
            InfoComposable(
                title = stringResource(R.string.text_composable_title),
                text = stringResource(R.string.text_composable_text),
                Modifier.weight(1f).fillMaxSize().background(Color(0xFFEADDFF))
            )
            InfoComposable(
                title = stringResource(R.string.image_composable_tittle),
                text = stringResource(R.string.image_composable_text),
                Modifier.weight(1f).fillMaxSize().background(Color(0xFFD0BCFF))
            )
        }
        Row(
            modifier = modifier
                .weight(1F)
                .fillMaxSize()
        ) {
            InfoComposable(
                title = stringResource(R.string.row_composable_tittle),
                text = stringResource(R.string.row_composable_text),
                Modifier.weight(1f).fillMaxSize().background(Color(0xFFB69DF8))
            )
            InfoComposable(
                title = stringResource(R.string.column_composable_tittle),
                text = stringResource(R.string.column_composable_text),
                Modifier.weight(1f).fillMaxSize().background(Color(0xFFF6EDFF))
            )
        }
    }
}

@Composable
fun InfoComposable(
    title: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Box (
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(16.dp)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 16.dp)
            )
            Text(
                text = text,
                textAlign = TextAlign.Justify
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ComposeQuadrantPreview() {
    ComposeQuadrantTheme {
        PrincipalFrame()
    }
}