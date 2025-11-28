package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    var screen by remember { mutableStateOf(1) }
    var taps by remember { mutableStateOf(0) }
    var tapRequired by remember { mutableStateOf((2..4).random()) }

    when (screen) {

        1 -> PrincipalFrame(
            image = R.drawable.lemon_tree,
            text = R.string.tree_text,
        ) {
            screen = 2
        }

        2 -> PrincipalFrame(
            image = R.drawable.lemon_squeeze,
            text = R.string.lemon_text,
        ) {
            taps++
            if (taps >= tapRequired) {
                screen = 3
                taps = 0
                tapRequired = (2..4).random()
            }
        }

        3 -> PrincipalFrame(
            image = R.drawable.lemon_drink,
            text = R.string.lemonade_text,
        ) {
            screen = 4
        }

        4 -> PrincipalFrame(
            image = R.drawable.lemon_restart,
            text = R.string.glass_text,
        ) {
            screen = 1
        }
    }
}

@Composable
fun LemonadeTitle(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .weight(1F)
                .fillMaxSize()
                .background(colorResource(R.color.super_title_color))
        ) {}

        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .weight(1F)
                .fillMaxSize()
                .background(colorResource(R.color.lemonade_title))
        ) {
            Text(
                text = stringResource(R.string.lemonade_title),
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp
            )
        }
    }
}

@Composable
fun PrincipalFrame(
    modifier: Modifier = Modifier,
    text: Int,
    image: Int,
    onClick: () -> Unit
) {
    Column(modifier = modifier) {
        LemonadeTitle(Modifier.weight(2F))

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.weight(13F).fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier.size(200.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(colorResource(R.color.background_image,))
                    .clickable(onClick = onClick)
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    modifier = modifier
                        .padding(12.dp)
                )
            }

            Text(
                text = stringResource(text),
                modifier = Modifier.padding(top = 24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}
