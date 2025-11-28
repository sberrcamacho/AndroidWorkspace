package com.example.businesscard

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Surface(
                    color = colorResource(R.color.background_color),
                    modifier = Modifier.fillMaxSize()
                ) {
                    MainFrame()
                }

            }
        }
    }
}
@Composable
fun MainFrame(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        val unit = 100.dp
        AndroidFrame(
            modifier = Modifier.weight(11F).fillMaxSize()
        )
        InfoFrame(
            modifier = Modifier.weight(4F).fillMaxSize()
        )
    }
}


@Composable
fun AndroidFrame(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.scale(0.9F)

    ) {
        val image = painterResource(R.drawable.android_logo)
        Box (
            modifier = Modifier
                .background(colorResource(R.color.background_icon))
        ){
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .size(110.dp)
                    .padding(5.dp)
            )
        }

        Text(
            text = stringResource(R.string.name_title),
            fontWeight = FontWeight.Light,
            fontSize = 50.sp,
            modifier = Modifier.padding(8.dp)
        )

        val color = colorResource(R.color.font_color)
        Text(
            text = stringResource(R.string.android_developer_text),
            fontWeight = FontWeight.Bold,
            color = color,
            fontSize = 17.sp
        )
    }
}

@Composable
fun InfoFrame(
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier.wrapContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val unit = 12.dp
        RowInfo(
            text = stringResource(R.string.telephone_number_text),
            icon = Icons.Default.Call,
            modifier = Modifier.padding(bottom = unit).align(Alignment.Start)
        )
        RowInfo(
            text = stringResource(R.string.social_network_text),
            icon = Icons.Default.Share,
            modifier = Modifier.align(Alignment.Start)

        )
        RowInfo(
            text = stringResource(R.string.mail_text),
            icon = Icons.Default.Email,
            modifier = Modifier.padding(top = unit).align(Alignment.Start)
        )
    }
}

@Composable
fun RowInfo(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
    ) {
        val color = colorResource(R.color.font_color)
        Icon (
            imageVector = icon,
            tint = color,
            contentDescription = null,
            modifier = Modifier.padding(end = 15.dp).size(22.dp)
        )

        Text(
            text = text,
        )
    }
}

@Preview(backgroundColor = 0xFFe5ffe3, showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        MainFrame()
    }
}