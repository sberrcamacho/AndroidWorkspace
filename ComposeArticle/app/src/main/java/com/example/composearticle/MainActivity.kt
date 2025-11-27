package com.example.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composearticle.ui.theme.ComposeArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeArticleTheme() {
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
    val image = painterResource(R.drawable.bg_compose_background)
    Column (
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    ) {
        Image (
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Text(
            text = stringResource(R.string.jetpack_compose_tutorial_title),
            fontSize = 24.sp,
            modifier = Modifier
                .padding(16.dp)
        )
        Text(
            text = stringResource(R.string.first_text),
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
        )
        Text(
            text = stringResource(R.string.second_text),
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ComposePreview() {
    ComposeArticleTheme {
        PrincipalFrame()
    }
}