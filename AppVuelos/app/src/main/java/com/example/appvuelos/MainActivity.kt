package com.example.appvuelos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.appvuelos.ui.theme.AppVuelosTheme
import com.example.appvuelos.ui.theme.OtherWhite

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppVuelosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = OtherWhite
                ) {
                    Logic()
                }
            }
        }
    }
}

