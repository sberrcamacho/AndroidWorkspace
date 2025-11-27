package com.example.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanager.ui.theme.TaskManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskManagerTheme {
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
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        val image = painterResource(R.drawable.ic_task_completed)
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Text(
            text = stringResource(R.string.all_tasks_completed_text),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 24.dp, bottom = 8.dp)
        )
        Text(
            text = stringResource(R.string.nice_work_text),
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TaskManagerPreview() {
    TaskManagerTheme {
        PrincipalFrame()
    }
}